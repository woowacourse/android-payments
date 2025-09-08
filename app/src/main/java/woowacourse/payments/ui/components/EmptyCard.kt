package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun EmptyCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFFE5E5E5),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Text(
            text = "+",
            fontSize = 34.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF575757),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCardPreview() {
    AndroidpaymentsTheme {
        EmptyCard(
            modifier = Modifier,
        )
    }
}
