package woowacourse.payments.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.core.mapper.asColor
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.debug.fixture.cardUiModelSample

@Composable
fun Card(
    cardUiModel: CardUiModel,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.background(
            color = cardUiModel.bankUiModel.cardColor.asColor(),
            shape = RoundedCornerShape(5.dp),
        )
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    Card(
        cardUiModelSample,
        modifier = Modifier
            .shadow(8.dp)
            .width(width = 208.dp)
    ) {
        CardContent(
            cardUiModel = cardUiModelSample,
            Modifier
                .padding(15.dp),
        )
    }
}
