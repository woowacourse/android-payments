package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.common.components.PaymentCard
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.common.model.toUiState
import woowacourse.payments.ui.newcard.components.CardExpirationDateTextField
import woowacourse.payments.ui.newcard.components.CardHolderNameTextField
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.CardPasswordTextField
import woowacourse.payments.ui.newcard.components.CompanySelectBottomSheet
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel
import woowacourse.payments.ui.newcard.model.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    card: CardUiModel? = null,
    companies: List<CardCompanyUiModel> = emptyList(),
    onBackClick: () -> Unit = {},
    onSaveClick: (CardUiModel) -> Unit = {},
) {
    val initialUiState: NewCardUiState = card?.toUiState() ?: NewCardUiState()
    val stateHolder: NewCardStateHolder =
        rememberNewCardState(initialUiState)
    val uiState: NewCardUiState = stateHolder.uiState
    var showBottomSheet: Boolean by rememberSaveable { mutableStateOf(true) }
    val bottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(key1 = uiState.cardCompany) {
        if (uiState.cardCompany != null) {
            showBottomSheet = false
        }
    }

    CompanySelectBottomSheet(
        companies = companies,
        onCompanySelected = { stateHolder.onCompanySelected(it) },
        sheetState = bottomSheetState,
        showBottomSheet = showBottomSheet,
        onDisMiss = onBackClick,
    )

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            NewCardTopBar(
                canSave = uiState != initialUiState && uiState.isCardValid,
                onBackClick = onBackClick,
                onSaveClick = { stateHolder.card?.let { onSaveClick(it) } },
                isEdit = card != null,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .verticalScroll(scrollState),
        ) {
            PaymentCard(
                card = stateHolder.card,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(20.dp))

            CardNumberTextField(
                value = uiState.cardNumber,
                onValueChange = stateHolder::onCardNumberChange,
                isValid = uiState.isCardNumberValid,
                modifier = Modifier.fillMaxWidth(),
            )
            CardExpirationDateTextField(
                value = uiState.cardExpirationDate,
                onValueChange = stateHolder::onCardExpirationDateChange,
                isValid = uiState.isCardExpirationDateValid,
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            CardHolderNameTextField(
                value = uiState.cardHolderName,
                onValueChange = stateHolder::onCardHolderNameChange,
                isValid = uiState.isCardHolderNameValid,
                modifier = Modifier.fillMaxWidth(),
            )
            CardPasswordTextField(
                value = uiState.cardPassword,
                onValueChange = stateHolder::onCardPasswordChange,
                isValid = uiState.isCardPasswordValid,
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen(companies = CardCompany.entries.map(CardCompany::toUiModel))
}
