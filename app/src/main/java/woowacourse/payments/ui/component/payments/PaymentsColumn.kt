package woowacourse.payments.ui.component.payments

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.theme.GrayE5

@Composable
fun PaymentsColumn(
    cards: List<PaymentCard> = emptyList(),
    onClickAddCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        if (cards.size <= 1) {
            Text(
                text = stringResource(woowacourse.payments.R.string.payments_enroll_new_card),
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        for (card in cards) {
            Box(
                modifier = Modifier
                    .shadow(8.dp)
                    .size(width = 208.dp, height = 124.dp)
                    .background(
                        color = Color(0xFF333333),
                        shape = RoundedCornerShape(5.dp),
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 14.dp, bottom = 10.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        )
                )
                Text(
                    modifier = Modifier.padding(start = 14.dp, bottom = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    text = "${card.cardNumber}",
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(start = 14.dp, bottom = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    text = "${card.cardOwnerName}",
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(end = 14.dp, bottom = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    text = "${card.expirationDate}",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
        }
        if (2 > cards.size) {
            Box(
                modifier = Modifier
                    .shadow(8.dp)
                    .size(width = 208.dp, height = 124.dp)
                    .background(
                        color = GrayE5,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clickable { onClickAddCard() }
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(woowacourse.payments.R.string.content_description_add_card),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentsColumnPreview() {
    PaymentsColumn(onClickAddCard = {})
}
