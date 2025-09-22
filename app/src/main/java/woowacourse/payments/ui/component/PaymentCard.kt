package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.extension.semanticsContentDescription
import woowacourse.payments.ui.model.BankTypeUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun PaymentCard(
    bankName: String,
    number: String,
    expirationDate: String,
    cardholderName: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.DarkGray,
    textStyle: TextStyle = paymentCardTextStyle(),
    cornerRadius: Int = 5,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp, RoundedCornerShape(cornerRadius.dp))
                .size(width = 208.dp, height = 124.dp)
                .background(backgroundColor, RoundedCornerShape(cornerRadius.dp))
                .padding(12.dp)
                .semanticsContentDescription(R.string.payment_card_content_description),
    ) {
        CompositionLocalProvider(LocalTextStyle provides textStyle) {
            Text(
                text = bankName,
                modifier = Modifier.align(Alignment.TopStart),
            )
            PaymentCardChip(modifier = Modifier.align(Alignment.CenterStart))
            PaymentCardDetails(
                number = number,
                expirationDate = expirationDate,
                cardholderName = cardholderName,
                modifier = Modifier.align(Alignment.BottomStart),
            )
        }
    }
}

@Composable
fun paymentCardTextStyle(): TextStyle =
    TextStyle(
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.sp,
        lineHeight = 1.em,
    )

@Composable
private fun PaymentCardChip(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFCBBA64),
) {
    Box(
        modifier =
            modifier
                .size(width = 40.dp, height = 26.dp)
                .background(color, RoundedCornerShape(4.dp)),
    )
}

@Composable
private fun PaymentCardDetails(
    number: String,
    expirationDate: String,
    cardholderName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = number,
            letterSpacing = 2.sp,
            maxLines = 1,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = cardholderName,
                maxLines = 1,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = expirationDate,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF333333)
@Composable
private fun PaymentCardPreview() {
    val paymentCardUiModel =
        PaymentCardUiModel(
            bankType = BankTypeUiModel.HYUNDAI,
            number = CardNumberUiModel("1234567812345678"),
            expirationDate = CardExpirationDateUiModel("1224"),
            cardholderName = CardholderNameUiModel("JOHN DOE", 30),
        )

    PaymentCard(
        bankName = "현대카드",
        number = paymentCardUiModel.displayCardNumber(),
        expirationDate = paymentCardUiModel.displayExpirationDate(),
        cardholderName = paymentCardUiModel.upperCardholderName,
    )
}
