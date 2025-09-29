package woowacourse.payments.ui.features.cardinput

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.data.PaymentInMemoryRepository
import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.ui.components.PaymentCardPlate
import woowacourse.payments.ui.features.cardinput.components.CardExpireDateField
import woowacourse.payments.ui.features.cardinput.components.CardNumberField
import woowacourse.payments.ui.features.cardinput.components.CardOwnerNameField
import woowacourse.payments.ui.features.cardinput.components.CardPasswordField
import woowacourse.payments.ui.features.cardinput.components.NewCardTopBar
import woowacourse.payments.ui.features.cardinput.components.bottomsheet.BottomSheetScreen
import woowacourse.payments.ui.mapper.CardCreationResult
import woowacourse.payments.ui.mapper.CardMapper.toDomainCard
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel.Companion.EMPTY_DB_ID
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private val SupportingTextHeight = 20.dp
private val FormFieldSpacing = 30.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInputScreen(
    dbId: Int = EMPTY_DB_ID,
    cardUiStateHolder: CardUiStateHolder,
    screenTitle: String,
    onNavigateBack: () -> Unit,
    onNavigateSave: (Int, PaymentCard) -> Unit,
) {
    val uiState by cardUiStateHolder.uiState
    val expireDateUiState by cardUiStateHolder.expireDateUiState
    val paymentCardUiModel by cardUiStateHolder.paymentCardUiModel

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by rememberSaveable { mutableStateOf(dbId == EMPTY_DB_ID) }

    var isSavingInProgress by remember { mutableStateOf(false) }

    val context = LocalContext.current

    @StringRes
    var toastMessageResId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(toastMessageResId) {
        toastMessageResId?.let { messageId ->
            Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
            toastMessageResId = null
        }
    }

    val attemptSave = {
        isSavingInProgress = true
        val cardDomainResult = uiState.toDomainCard()
        when (cardDomainResult) {
            CardCreationResult.UnknownCardCompany -> showBottomSheet = true

            is CardCreationResult.Success -> {
                isSavingInProgress = false
                if (dbId != EMPTY_DB_ID) {
                    val dbState = PaymentInMemoryRepository.findById(dbId)

                    if (dbState == cardUiStateHolder.uiState.value) {
                        isSavingInProgress = false
                        toastMessageResId = R.string.edit_card_same_alert
                    } else {
                        PaymentInMemoryRepository.update(dbId, uiState)
                        onNavigateSave(dbId, cardDomainResult.paymentCard)
                    }
                } else {
                    val savedDBId = PaymentInMemoryRepository.add(uiState)
                    onNavigateSave(savedDBId, cardDomainResult.paymentCard)
                }
            }

            else -> {
                isSavingInProgress = false
                toastMessageResId =
                    when (cardDomainResult) {
                        CardCreationResult.InvalidCardNumber ->
                            R.string.card_list_incomplete_card_number_field_alert

                        is CardCreationResult.InvalidExpireDate ->
                            R.string.card_list_incomplete_expire_date_field_alert

                        CardCreationResult.InvalidPassword ->
                            R.string.card_list_incomplete_card_password_field_alert

                        else -> null
                    }
            }
        }
    }

    LaunchedEffect(uiState.cardCompanyUiModel) {
        if (isSavingInProgress && uiState.cardCompanyUiModel == CardCompanyUiModel.UNKNOWN) {
            attemptSave()
        }
    }

    if (showBottomSheet) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {
                showBottomSheet = false
                isSavingInProgress = false
            },
            onItemClick = { selectedCard: CardCompanyUiModel ->
                cardUiStateHolder.updateCardCompany(selectedCard)
                showBottomSheet = false
                if (isSavingInProgress) {
                    attemptSave()
                }
            },
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                title = screenTitle,
                onBackClick = onNavigateBack,
                onSaveClick = attemptSave,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            PaymentCardPlate(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { showBottomSheet = true },
                paymentCardUiModel = paymentCardUiModel,
            )
            Spacer(modifier = Modifier.height(40.dp))
            CardNumberField(
                value = uiState.cardNumber,
                onValueChange = { cardUiStateHolder.updateCardNumber(it) },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardExpireDateField(
                value = uiState.expireDate,
                onValueChange = { cardUiStateHolder.updateExpireDate(it) },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f),
                expireDateUiState = expireDateUiState,
                supportingTextHeight = SupportingTextHeight,
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardOwnerNameField(
                value = uiState.ownerName,
                onValueChange = { cardUiStateHolder.updateOwnerName(it) },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardPasswordField(
                value = uiState.password,
                onValueChange = { cardUiStateHolder.updatePassword(it) },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        CardInputScreen(
            1,
            cardUiStateHolder = CardUiStateHolder(),
            screenTitle = "카드 입력 화면",
            {},
            { _, _ -> },
        )
    }
}
