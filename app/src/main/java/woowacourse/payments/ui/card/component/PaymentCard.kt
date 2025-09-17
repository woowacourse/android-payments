package woowacourse.payments.ui.card.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardNumber: String? = null,
    expiredDate: String? = null,
    ownerName: String? = null,
    bankName: String? = null,
) {
    Column(
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Text(
            text = bankName ?: "",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 14.dp, top = 12.dp),
        )
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, top = 16.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
        Column(
            modifier =
                Modifier
                    .padding(start = 14.dp, end = 14.dp, top = 16.dp),
        ) {
            Text(text = cardNumber ?: "", color = Color.White, fontSize = 12.sp, lineHeight = 12.sp)
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = ownerName ?: "",
                    color = Color.White,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                )
                Text(
                    text = expiredDate ?: "",
                    color = Color.White,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun PaymentCardPreview() {
    PaymentCard(
        cardNumber = "1234 - 5678 - 1234 - 5678",
        expiredDate = "11/24",
        ownerName = "TAMA SEO",
        bankName = "국민카드",
    )
}
