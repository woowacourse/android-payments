package woowacourse.payments.view.cardediting.component

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
import woowacourse.payments.view.cardediting.CardEditingUiEvent
import woowacourse.payments.view.cardediting.CardEditingUiState
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
fun CardEditingScreen(
    state: CardEditingUiState,
    onUiEvent: (CardEditingUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    if (!state.edited.isBankSelected) {
        BankSelectBottomSheet({ bankType: BankTypeUiModel ->
            onUiEvent(
                CardEditingUiEvent.UpdateBankType(
                    bankType,
                ),
            )
        })
    }

    Scaffold(
        modifier = modifier.testTag("CardEditingScreen"),
        topBar = {
            CardEditingTopAppBar(
                onUiEvent = onUiEvent,
                checkEnabled = state.canEditCard,
            )
        },
    ) { paddingValues ->
        CardEditingContent(
            card = state.edited,
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
private fun CardEditingContent(
    card: CardUiModel,
    onUiEvent: (CardEditingUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PaymentCard(
            onClick = { onUiEvent(CardEditingUiEvent.UpdateBankType(null)) },
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
            onValueChange = { value -> onUiEvent(CardEditingUiEvent.UpdateCardNumber(value)) },
            isError = !card.isValidCardNumber,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
        )
        ExpiredDateTextField(
            value = card.expiredDate,
            onValueChange = { value -> onUiEvent(CardEditingUiEvent.UpdateExpiredDate(value)) },
            isError = !card.isValidExpiredDate,
            modifier = Modifier.padding(top = 18.dp),
        )
        CardOwnerNameTextField(
            value = card.holder,
            onValueChange = { value -> onUiEvent(CardEditingUiEvent.UpdateHolder(value)) },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp),
            maxLength = card.holderMaxLength,
        )
        PasswordTextField(
            value = card.password,
            onValueChange = { value -> onUiEvent(CardEditingUiEvent.UpdatePassword(value)) },
            isError = !card.isValidPassword,
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
        state = state,
        onUiEvent = {},
    )
}

private class CardEditingScreenPreviewParameterProvider : PreviewParameterProvider<CardEditingUiState> {
    override val values: Sequence<CardEditingUiState> =
        sequenceOf(
            CardEditingUiState(
                original =
                    CardUiModel(
                        bankType = BankTypeUiModel.BC,
                        number = "",
                        expiredDate = "",
                        holder = "",
                        password = "",
                    ),
            ),
            CardEditingUiState(
                original =
                    CardUiModel(
                        bankType = BankTypeUiModel.BC,
                        number = "1234",
                        expiredDate = "125",
                        holder = "",
                        password = "12",
                    ),
            ),
            CardEditingUiState(
                original =
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
