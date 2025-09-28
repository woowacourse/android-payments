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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.view.cardaddition.CardAdditionUiEvent
import woowacourse.payments.view.cardaddition.CardAdditionUiState
import woowacourse.payments.view.ui.component.BankSelectBottomSheet
import woowacourse.payments.view.ui.component.CardNumberTextField
import woowacourse.payments.view.ui.component.CardOwnerNameTextField
import woowacourse.payments.view.ui.component.ExpiredDateTextField
import woowacourse.payments.view.ui.component.PasswordTextField
import woowacourse.payments.view.ui.component.PaymentCard
import woowacourse.payments.view.ui.model.BankTypeUiModel
import woowacourse.payments.view.ui.model.CardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAdditionScreen(
    state: CardAdditionUiState,
    onUiEvent: (CardAdditionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    if (!state.card.isBankSelected) {
        BankSelectBottomSheet(onSelectBankType = { bankType: BankTypeUiModel ->
            onUiEvent(
                CardAdditionUiEvent.UpdateBankType(
                    bankType,
                ),
            )
        })
    }

    Scaffold(
        modifier = modifier.testTag("CardAdditionScreen"),
        topBar = {
            CardAdditionTopAppBar(
                checkEnabled = state.card.isValid,
                onUiEvent = onUiEvent,
            )
        },
    ) { paddingValues: PaddingValues ->
        CardAdditionContent(
            card = state.card,
            onUiEvent = onUiEvent,
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
    onUiEvent: (CardAdditionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PaymentCard(
            onClick = { onUiEvent(CardAdditionUiEvent.UpdateBankType(null)) },
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
            onValueChange = { value -> onUiEvent(CardAdditionUiEvent.UpdateCardNumber(value)) },
            isError = !card.isValidCardNumber,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
        )
        ExpiredDateTextField(
            value = card.expiredDate,
            onValueChange = { value -> onUiEvent(CardAdditionUiEvent.UpdateExpiredDate(value)) },
            isError = !card.isValidExpiredDate,
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )
        CardOwnerNameTextField(
            value = card.holder,
            onValueChange = { value -> onUiEvent(CardAdditionUiEvent.UpdateHolder(value)) },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp),
            maxLength = card.holderMaxLength,
        )
        PasswordTextField(
            value = card.password,
            onValueChange = { value -> onUiEvent(CardAdditionUiEvent.UpdatePassword(value)) },
            isError = !card.isValidPassword,
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
        state = state,
        onUiEvent = {},
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
