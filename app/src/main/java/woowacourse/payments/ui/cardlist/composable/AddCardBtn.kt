package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun AddCardBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize().clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "+", fontSize = 34.sp, color = Color(0xFF575757))
    }
}
