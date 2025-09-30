package woowacourse.payments.view.cardediting.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.view.cardediting.CardEditingUiEvent
import woowacourse.payments.view.cardediting.CardEditingUiState
import woowacourse.payments.view.ui.component.BankSelectBottomSheet
import woowacourse.payments.view.ui.component.CardForm
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
        CardForm(
            card = state.edited,
            onCardNumberChange = { value -> onUiEvent(CardEditingUiEvent.UpdateCardNumber(value)) },
            onExpiredDateChange = { value -> onUiEvent(CardEditingUiEvent.UpdateExpiredDate(value)) },
            onHolderChange = { value -> onUiEvent(CardEditingUiEvent.UpdateHolder(value)) },
            onPasswordChange = { value -> onUiEvent(CardEditingUiEvent.UpdatePassword(value)) },
            onClearBankType = { onUiEvent(CardEditingUiEvent.UpdateBankType(null)) },
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
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
