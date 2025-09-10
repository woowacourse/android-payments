package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        content()
    }
}
