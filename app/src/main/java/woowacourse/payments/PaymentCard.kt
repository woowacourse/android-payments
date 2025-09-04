package woowacourse.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCard(modifier: Modifier = Modifier) {
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
        Box(
            modifier =
                modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(40.dp, 28.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(5.dp),
                    ),
        )
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}
