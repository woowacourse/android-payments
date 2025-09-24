package woowacourse.payments.cardediting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.BankType
import woowacourse.payments.CardUiModel
import woowacourse.payments.cardaddition.component.BankSelectBottomSheet
import woowacourse.payments.cardaddition.component.CardNumberTextField
import woowacourse.payments.cardaddition.component.CardOwnerNameTextField
import woowacourse.payments.cardaddition.component.ExpiredDateTextField
import woowacourse.payments.cardaddition.component.PasswordTextField
import woowacourse.payments.cardediting.CardEditingStateHolder
import woowacourse.payments.cardediting.CardEditingUiState
import woowacourse.payments.ui.component.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEditingScreen(
    stateHolder: CardEditingStateHolder,
    onBackClick: () -> Unit,
    onCheckClick: (old: CardUiModel, new: CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state: CardEditingUiState = stateHolder.uiState
    val scrollState = rememberScrollState()

    if (!state.isBankSelected) {
        BankSelectBottomSheet(onSelectBankType = stateHolder::updateBankType)
    }

    Scaffold(
        modifier = modifier.testTag("CardEditingScreen"),
        topBar = {
            CardEditingTopAppBar(
                onBackClick = onBackClick,
                onCheckClick = { onCheckClick(state.original, state.edited) },
                checkEnabled = state.canEditCard,
            )
        },
    ) { paddingValues ->
        CardEditingContent(
            card = state.edited,
            onCardNumberChange = stateHolder::updateCardNumber,
            isNumberError = !state.isValidCardNumber,
            onExpiredDateChange = stateHolder::updateExpiredDate,
            isExpiredDateError = !state.isValidExpiredDate,
            onHolderChange = stateHolder::updateHolder,
            holderMaxLength = state.edited.holderMaxLength,
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
private fun CardEditingContent(
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
    Column(modifier = modifier) {
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
            modifier = Modifier.padding(top = 18.dp),
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
private fun CardEditingScreenPreview(
    @PreviewParameter(CardEditingScreenPreviewParameterProvider::class) state: CardEditingUiState,
) {
    CardEditingScreen(
        onBackClick = {},
        onCheckClick = { _, _ -> },
        stateHolder = CardEditingStateHolder(state),
    )
}

private class CardEditingScreenPreviewParameterProvider : PreviewParameterProvider<CardEditingUiState> {
    override val values: Sequence<CardEditingUiState> =
        sequenceOf(
            CardEditingUiState(
                original =
                    CardUiModel(
                        bankType = BankType.BC,
                        number = "",
                        expiredDate = "",
                        holder = "",
                        password = "",
                    ),
            ),
            CardEditingUiState(
                original =
                    CardUiModel(
                        bankType = BankType.BC,
                        number = "1234",
                        expiredDate = "125",
                        holder = "",
                        password = "12",
                    ),
            ),
            CardEditingUiState(
                original =
                    CardUiModel(
                        bankType = BankType.BC,
                        number = "1234".repeat(4),
                        expiredDate = "1225",
                        holder = "CREW",
                        password = "1234",
                    ),
            ),
        )
}
