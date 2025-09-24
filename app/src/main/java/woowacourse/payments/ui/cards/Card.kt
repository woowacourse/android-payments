package woowacourse.payments.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cards.core.mapper.asColor
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.debug.fixture.paymentCardUiModelSample

@Composable
fun PaymentCard(
    paymentCardUiModel: PaymentCardUiModel,
    onCardClick: (PaymentCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bankUiModel = paymentCardUiModel.bankUiModel
    Box(
        modifier =
            modifier
                .shadow(8.dp)
                .width(width = 208.dp)
                .background(
                    color = bankUiModel.cardColor.asColor(),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        CardContent(
            paymentCardUiModel,
            Modifier
                .padding(15.dp)
                .clickable{onCardClick(paymentCardUiModel)},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    PaymentCard(paymentCardUiModelSample,{})
}
