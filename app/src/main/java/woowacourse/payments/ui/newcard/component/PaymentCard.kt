package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun PaymentCard(
    card: Card? = null,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart)
        )
        card?.let {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, bottom = 32.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                text = "${card.number.value.take(4)} - ${
                    card.number.value.drop(4).take(4)
                } - **** - ****",
                color = Color.White,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, bottom = 16.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                text = "${card.ownerName.value}",
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 14.dp, bottom = 16.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                text = "${card.expirationDate.value.format(DateTimeFormatter.ofPattern("MM/yy"))}",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    val card = Card(
        number = CardNumber("1234567890123456"),
        ownerName = OwnerName("Hwang Chaewon"),
        expirationDate = ExpirationDate(YearMonth.now().plusYears(1)),
        password = Password("1234")
    )
    PaymentCard(card)
}