package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.core.ext.toSignatureColor
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.theme.Black33
import woowacourse.payments.ui.theme.GrayE5

@Composable
fun PaymentCard(
    cardState: CardState,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (contentAlignment, backgroundColor) =
        when (cardState) {
            CardState.Empty -> Alignment.Center to GrayE5
            is CardState.Pending -> Alignment.CenterStart to Black33
            is CardState.Registered ->
                Alignment.CenterStart to cardState.card.company.toSignatureColor()
        }

    Box(
        contentAlignment = contentAlignment,
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        content()
    }
}
