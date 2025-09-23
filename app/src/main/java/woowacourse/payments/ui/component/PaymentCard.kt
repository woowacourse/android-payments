package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.component.preview.PaymentCardPreviewProvider
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.CardTextStyle

@Composable
fun PaymentCard(
    paymentCard: PaymentCardUiModel?,
    onSelectBank: () -> Unit,
    onEditCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bank = paymentCard?.bank ?: BankUiModel.PlaceHolder

    Box(
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(bank.colorInt),
                    shape = RoundedCornerShape(5.dp),
                )
                .clickable(onClick = onEditCard),
    ) {
        Text(
            text = stringResource(bank.nameRes),
            style = CardTextStyle,
            color = Color.White,
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 15.dp, start = 14.dp),
        )

        Column(
            modifier =
                Modifier
                    .padding(start = 14.dp, top = 44.dp, end = 14.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            if (paymentCard != null) {
                Text(
                    text = paymentCard.maskedCardNumber(stringResource(R.string.card_number_separator)),
                    style = CardTextStyle,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp),
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = paymentCard.owner,
                        style = CardTextStyle,
                        color = Color.White,
                    )
                    Text(
                        text =
                            paymentCard.formattedExpiry(stringResource(R.string.expiry_separator)),
                        style = CardTextStyle,
                        color = Color.White,
                    )
                }
            }
        }

        if (!bank.isSelected) {
            SelectBankHint(
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .clickable(onClick = onSelectBank),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentCardPreviewProvider::class)
    uiModel: PaymentCardUiModel,
) {
    PaymentCard(
        paymentCard = uiModel,
        onSelectBank = {},
        onEditCard = {},
        modifier = Modifier,
    )
}
