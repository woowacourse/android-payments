package woowacourse.payments.view.cardaddition.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.view.BankTypeUiModel
import woowacourse.payments.view.CardUiModel
import woowacourse.payments.view.cardaddition.CardAdditionStateHolder
import woowacourse.payments.view.cardaddition.CardAdditionUiEvent
import woowacourse.payments.view.cardaddition.CardAdditionUiState
import woowacourse.payments.view.cardaddition.rememberCardAdditionStateHolder
import woowacourse.payments.view.ui.component.BankSelectBottomSheet
import woowacourse.payments.view.ui.component.CardNumberTextField
import woowacourse.payments.view.ui.component.CardOwnerNameTextField
import woowacourse.payments.view.ui.component.ExpiredDateTextField
import woowacourse.payments.view.ui.component.PasswordTextField
import woowacourse.payments.view.ui.component.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAdditionScreen(
    onBackClick: () -> Unit,
    onCardSaveSuccess: () -> Unit,
    onCardSaveFailure: () -> Unit,
    modifier: Modifier = Modifier,
    stateHolder: CardAdditionStateHolder = rememberCardAdditionStateHolder(),
) {
    val state: CardAdditionUiState = stateHolder.uiState
    val event: CardAdditionUiEvent? = stateHolder.uiEvent

    val scrollState = rememberScrollState()

    if (!state.isBankSelected) {
        BankSelectBottomSheet(stateHolder::updateBankType)
    }

    LaunchedEffect(event) {
        when (event) {
            CardAdditionUiEvent.AddCardSuccess -> onCardSaveSuccess()
            CardAdditionUiEvent.AddCardFailure -> onCardSaveFailure()
            null -> Unit
        }

        stateHolder.clearEvent()
    }

    Scaffold(
        modifier = modifier.testTag("CardAdditionScreen"),
        topBar = {
            CardAdditionTopAppBar(
                onBackClick = onBackClick,
                onCheckClick = stateHolder::addCard,
                checkEnabled = state.canAddCard,
            )
        },
    ) { paddingValues: PaddingValues ->
        CardAdditionContent(
            card = state.card,
            onCardNumberChange = stateHolder::updateCardNumber,
            isNumberError = !state.isValidCardNumber,
            onExpiredDateChange = stateHolder::updateExpiredDate,
            isExpiredDateError = !state.isValidExpiredDate,
            onHolderChange = stateHolder::updateHolder,
            holderMaxLength = state.card.holderMaxLength,
            onPasswordChange = stateHolder::updatePassword,
            isPasswordError = !state.isValidPassword,
            onClearBankType = { stateHolder.updateBankType(null) },
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
        )
    }
}

@Composable
private fun CardAdditionContent(
    card: CardUiModel,
    onCardNumberChange: (String) -> Unit,
    isNumberError: Boolean,
    onExpiredDateChange: (String) -> Unit,
    isExpiredDateError: Boolean,
    onHolderChange: (String) -> Unit,
    holderMaxLength: Int,
    onPasswordChange: (String) -> Unit,
    isPasswordError: Boolean,
    onClearBankType: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PaymentCard(
            onClick = onClearBankType,
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 14.dp, bottom = 28.dp),
            number = card.number,
            owner = card.holder,
            expiredDate = card.expiredDate,
            bankType = card.bankType,
        )
        CardNumberTextField(
            value = card.number,
            onValueChange = onCardNumberChange,
            isError = isNumberError,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
        )
        ExpiredDateTextField(
            value = card.expiredDate,
            onValueChange = onExpiredDateChange,
            isError = isExpiredDateError,
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )
        CardOwnerNameTextField(
            value = card.holder,
            onValueChange = onHolderChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp),
            maxLength = holderMaxLength,
        )
        PasswordTextField(
            value = card.password,
            onValueChange = onPasswordChange,
            isError = isPasswordError,
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun CardAdditionScreenPreview(
    @PreviewParameter(CardAdditionScreenPreviewParameterProvider::class) state: CardAdditionUiState,
) {
    CardAdditionScreen(
        onBackClick = {},
        onCardSaveSuccess = {},
        onCardSaveFailure = {},
    )
}

private class CardAdditionScreenPreviewParameterProvider : PreviewParameterProvider<CardAdditionUiState> {
    override val values: Sequence<CardAdditionUiState> =
        sequenceOf(
            CardAdditionUiState(
                card =
                    CardUiModel(
                        bankType = BankTypeUiModel.BC,
                        number = "",
                        expiredDate = "",
                        holder = "",
                        password = "",
                    ),
            ),
            CardAdditionUiState(
                card =
                    CardUiModel(
                        bankType = BankTypeUiModel.BC,
                        number = "1234",
                        expiredDate = "125",
                        holder = "",
                        password = "12",
                    ),
            ),
            CardAdditionUiState(
                card =
                    CardUiModel(
                        bankType = BankTypeUiModel.BC,
                        number = "1234".repeat(4),
                        expiredDate = "1225",
                        holder = "CREW",
                        password = "1234",
                    ),
            ),
        )
}
