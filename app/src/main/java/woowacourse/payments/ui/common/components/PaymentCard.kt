package woowacourse.payments.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: CardUiModel? = null,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ).padding(horizontal = 14.dp, vertical = 16.dp),
    ) {
        Column {
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = card?.number ?: "",
                fontSize = 12.sp,
                letterSpacing = 2.sp,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = card?.holderName ?: "",
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    color = Color.White,
                )
                Text(
                    text = card?.expirationDate ?: "",
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(name = "카드 정보 없음")
@Composable
private fun PaymentCardPreview1() {
    PaymentCard()
}

@Preview(name = "카드 정보 있음")
@Composable
private fun PaymentCardPreview2() {
    PaymentCard(
        card =
            CardUiModel(
                number = "1111 - 2222 - 3333 - 4444",
                expirationDate = "09 / 25",
                holderName = "CREW",
            ),
    )
}
