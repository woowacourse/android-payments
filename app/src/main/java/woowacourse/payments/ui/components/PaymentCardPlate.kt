package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.card.values.CardNumber
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCardPlate(
    modifier: Modifier = Modifier,
    paymentCardUiModel: PaymentCardUiModel? = null,
) {
    val cardModel = paymentCardUiModel ?: PaymentCardUiModel.EMPTY
    val description =
        if (cardModel == PaymentCardUiModel.EMPTY) {
            stringResource(R.string.payment_card_empty_description)
        } else {
            stringResource(
                R.string.payment_card_full_description,
                cardModel.formattedCardNumber,
                cardModel.ownerName,
                cardModel.formattedExpireDate,
            )
        }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = cardModel.cardCompanyUiModel.plateColor,
                    shape = RoundedCornerShape(5.dp),
                ).semantics {
                    contentDescription = description
                },
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 16.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )

        Text(
            stringResource(cardModel.cardCompanyUiModel.companyNameResId),
            modifier =
                Modifier
                    .padding(start = 14.dp, top = 10.dp)
                    .align(Alignment.TopStart),
            style =
                LocalTextStyle.current.copy(
                    lineHeightStyle =
                        LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        ),
                ),
            fontSize = 12.sp,
            letterSpacing = 0.1.em,
            color = cardModel.cardCompanyUiModel.textColor,
        )

        Text(
            cardModel.formattedCardNumber,
            modifier =
                Modifier
                    .padding(start = 13.dp, bottom = 28.dp)
                    .align(Alignment.BottomStart),
            fontSize = 12.sp,
            letterSpacing = 0.17.em,
            color = cardModel.cardCompanyUiModel.textColor,
        )

        Text(
            cardModel.ownerName,
            modifier =
                Modifier
                    .padding(start = 13.dp, bottom = 10.dp)
                    .align(Alignment.BottomStart),
            fontSize = 12.sp,
            letterSpacing = 0.1.em,
            color = cardModel.cardCompanyUiModel.textColor,
        )

        Text(
            cardModel.formattedExpireDate,
            modifier =
                Modifier
                    .padding(end = 14.dp, bottom = 10.dp)
                    .align(Alignment.BottomEnd),
            fontSize = 12.sp,
            letterSpacing = 0.1.em,
            color = cardModel.cardCompanyUiModel.textColor,
        )
    }
}

fun CardNumber.toMaskedString(): String {
    val firstPart = this.value.substring(0, 4)
    val secondPart = this.value.substring(4, 8)
    return "$firstPart - $secondPart - **** - ****"
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCardPlate()
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardDetailPreview() {
    AndroidpaymentsTheme {
        PaymentCardPlate(
            paymentCardUiModel =
                PaymentCardUiModel(
                    CardCompanyUiModel.BC,
                    "1234 - 1234 - 1234 - 1234",
                    "02 / 26",
                    "CREW",
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardDetailKakaoPreview() {
    AndroidpaymentsTheme {
        PaymentCardPlate(
            paymentCardUiModel =
                PaymentCardUiModel(
                    CardCompanyUiModel.KAKAO,
                    "1234 - 1234 - 1234 - 1234",
                    "02 / 26",
                    "CREW",
                ),
        )
    }
}
