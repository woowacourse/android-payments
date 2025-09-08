package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.allcards.util.CardFormatter
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun Card(cardInfoUiState: CardInfoUiState) {
    Box(
        modifier = Modifier
            .height(124.dp)
            .shadow(8.dp)
            .width(208.dp)
            .background(
                color = colorResource(id = R.color.payments_card_background),
                shape = RoundedCornerShape(5.dp),
            )

    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 8.dp)
                .shadow(8.dp)
                .align(Alignment.CenterStart)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = colorResource(id = R.color.payments_card_chip),
                    shape = RoundedCornerShape(5.dp),
                )
        )
        if (!cardInfoUiState.isComplete()) return
        Column(
            modifier = Modifier
                .padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 10.dp
                )
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            Text(
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                style = TextStyle(letterSpacing = 2.sp),
                text = CardFormatter.formatCardNumber(cardInfoUiState.cardNumber),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.width(90.dp),
                    color = colorResource(id = R.color.white),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    text = cardInfoUiState.ownerName,
                )
                Text(
                    color = colorResource(id = R.color.white),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    text = CardFormatter.formatExpirationDate(cardInfoUiState.expireDate),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    AndroidpaymentsTheme {
        Card(
            CardInfoUiState(
                _cardNumber = "9999999999999999",
                _expireDate = "12/25",
                _ownerName = "홍길동홍길동홍길동홍길동홍길동",
                _password = "1234"
            )
        )
    }

}