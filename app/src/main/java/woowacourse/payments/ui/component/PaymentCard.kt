package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.core.CardType

@Composable
fun PaymentCard(
    cardType: CardType,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: (CardType) -> Unit = {},
) {
    Box(
        contentAlignment = cardType.parentAlignment,
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = cardType.backgroundColor,
                shape = RoundedCornerShape(5.dp),
            )
            .clickable(onClick = { onClick(cardType) })
    ) {
        content()
    }
}
