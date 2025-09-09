package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    cardContent: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        cardContent()
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        modifier =
            Modifier
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp),
                ),
    )
}
