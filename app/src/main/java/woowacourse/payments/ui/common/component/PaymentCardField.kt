package woowacourse.payments.ui.common.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.payments.model.BankUiModel

@Composable
fun PaymentCardField(
    paymentCardUiModel: PaymentCardUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = colorResource(paymentCardUiModel.bankUiModel.bankColor),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Text(
            text =
                if (paymentCardUiModel.bankUiModel != BankUiModel.NOT_SELECTED) {
                    stringResource(
                        paymentCardUiModel.bankUiModel.bankName,
                    )
                } else {
                    ""
                },
            color = Color.White,
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 15.dp, start = 14.dp),
        )
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ).align(Alignment.CenterStart),
        )

        if (paymentCardUiModel.bankUiModel != BankUiModel.NOT_SELECTED) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
            ) {
                Text(
                    text = paymentCardUiModel.formatNumber(),
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 13.sp,
                    letterSpacing = 3.5.sp,
                    lineHeight = 1.em,
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 2.dp),
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = if (paymentCardUiModel.cardholderName.isBlank()) "CREW" else paymentCardUiModel.cardholderName,
                        color = Color.White,
                        fontWeight = FontWeight.W500,
                        fontSize = 13.sp,
                        lineHeight = 1.em,
                        modifier =
                            Modifier
                                .weight(1f)
                                .padding(end = 2.dp),
                        maxLines = 1,
                    )

                    Text(
                        text = paymentCardUiModel.formatExpirationDate(),
                        color = Color.White,
                        fontWeight = FontWeight.W500,
                        lineHeight = 1.em,
                        fontSize = 13.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardFieldPreview(
    @PreviewParameter(PaymentCardFieldPreviewParameterProvider::class) paymentCardUiModel: PaymentCardUiModel,
) {
    Column(
        modifier = Modifier.padding(20.dp),
    ) {
        PaymentCardField(
            paymentCardUiModel = paymentCardUiModel,
        )
    }
}

private class PaymentCardFieldPreviewParameterProvider : PreviewParameterProvider<PaymentCardUiModel?> {
    override val values: Sequence<PaymentCardUiModel> =
        sequenceOf(
            PaymentCardUiModel("", "", "", bankUiModel = BankUiModel.NOT_SELECTED),
            PaymentCardUiModel("1111111111111111", "0421", "CREW", BankUiModel.SHINHAN),
            PaymentCardUiModel(
                "2222222222222222",
                "0522",
                "ABCDEABCDEABCDEABCDEABCDEABCDE",
                BankUiModel.KB,
            ),
        )
}
