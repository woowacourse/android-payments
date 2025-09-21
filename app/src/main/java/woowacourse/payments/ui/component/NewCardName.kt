package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewCardName(companyName: String?) {
    companyName?.let {
        Text(
            text = companyName,
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = W500,
            letterSpacing = 0.1.sp,
            modifier = Modifier.padding(horizontal = 13.dp),
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Preview
@Composable
fun NewCardNamePreview() {
    val companyName = "신한카드"
    NewCardName(companyName)
}
