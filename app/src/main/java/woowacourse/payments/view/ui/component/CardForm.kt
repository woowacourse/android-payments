package woowacourse.payments.view.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.view.ui.model.BankTypeUiModel
import woowacourse.payments.view.ui.model.CardUiModel

@Composable
fun CardForm(
    card: CardUiModel,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onHolderChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
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
            isError = !card.isValidCardNumber,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
        )

        ExpiredDateTextField(
            value = card.expiredDate,
            onValueChange = onExpiredDateChange,
            isError = !card.isValidExpiredDate,
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
            maxLength = card.holderMaxLength,
        )

        PasswordTextField(
            value = card.password,
            onValueChange = onPasswordChange,
            isError = !card.isValidPassword,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardFormPreview(
    @PreviewParameter(CardFormPreviewParameterProvider::class) card: CardUiModel,
) {
    CardForm(
        card = card,
        onCardNumberChange = {},
        onExpiredDateChange = {},
        onHolderChange = {},
        onPasswordChange = {},
        onClearBankType = {},
    )
}

private class CardFormPreviewParameterProvider : PreviewParameterProvider<CardUiModel> {
    override val values: Sequence<CardUiModel> =
        sequenceOf(
            CardUiModel(),
            CardUiModel(
                number = "1234".repeat(4),
                expiredDate = "0421",
                holder = "CREW",
                holderMaxLength = 30,
                password = "1234",
                bankType = BankTypeUiModel.BC,
            ),
        )
}
