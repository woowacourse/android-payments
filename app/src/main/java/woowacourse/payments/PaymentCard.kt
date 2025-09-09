package woowacourse.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCard(
    card: Card? = null,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .size(208.dp, 124.dp)
                .shadow(8.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 28.dp)
        ) {
            Box(
                modifier =
                    modifier
                        .size(40.dp, 28.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(5.dp),
                        ),
            )

            if (card != null) {
                Text(
                    text = card.number,
                    color = Color.White,
                    fontSize = 12.sp,
                    letterSpacing = 2.04.sp,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = card.name ?: "",
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 2.sp,
                    )
                    Text(
                        text = card.expiry,
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 1.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        Column {
            PaymentCard(Card("0000000000000000", "1025", "1234", "CREW"))
            PaymentCard()
        }
    }
}
