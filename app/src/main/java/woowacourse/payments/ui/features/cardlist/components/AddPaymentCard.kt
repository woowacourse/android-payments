package woowacourse.payments.ui.features.cardlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Black800
import woowacourse.payments.ui.theme.LightGray100

@Composable
fun AddPaymentCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = LightGray100,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Text(
            text = "+",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 34.sp,
            letterSpacing = 0.em,
            fontWeight = FontWeight.Normal,
            color = Black800,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        AddPaymentCard()
    }
}
