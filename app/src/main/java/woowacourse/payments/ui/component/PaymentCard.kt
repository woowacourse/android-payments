package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
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
    cornerRadius: Int = 5,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp, RoundedCornerShape(cornerRadius.dp))
                .size(width = 208.dp, height = 124.dp)
                .background(backgroundColor, RoundedCornerShape(cornerRadius.dp))
                .padding(12.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            PaymentCardText(bankName)
            PaymentCardChip()
            PaymentCardInfoBlock(
                number = number,
                expirationDate = expirationDate,
                cardholderName = cardholderName,
            )
        }
    }
}

@Composable
private fun PaymentCardText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    fontSize: TextUnit = 12.sp,
    letterSpacing: TextUnit = 0.sp,
    lineHeight: TextUnit = 1.em,
    fontWeight: FontWeight = FontWeight.W500,
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        modifier = modifier,
        maxLines = 1,
    )
}

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
private fun PaymentCardInfoBlock(
    number: String,
    expirationDate: String,
    cardholderName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        PaymentCardText(text = number, letterSpacing = 2.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PaymentCardText(
                text = cardholderName,
                modifier = Modifier.weight(1f),
            )
            PaymentCardText(text = expirationDate)
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

@Preview(showBackground = true)
@Composable
private fun PaymentCardChipPreview() {
    PaymentCardChip()
}

@Preview(showBackground = true, backgroundColor = 0xFF333333)
@Composable
private fun PaymentCardInfoBlockPreview() {
    val paymentCardUiModel =
        PaymentCardUiModel(
            bankType = BankTypeUiModel.HYUNDAI,
            number = CardNumberUiModel("1234567812345678"),
            expirationDate = CardExpirationDateUiModel("1224"),
            cardholderName = CardholderNameUiModel("JOHN DOE", 30),
        )
    PaymentCardInfoBlock(
        number = paymentCardUiModel.displayCardNumber(),
        expirationDate = paymentCardUiModel.displayExpirationDate(),
        cardholderName = paymentCardUiModel.upperCardholderName,
        modifier = Modifier.padding(12.dp),
    )
}
