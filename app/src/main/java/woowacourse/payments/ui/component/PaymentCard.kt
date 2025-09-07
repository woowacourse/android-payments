package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.CardType
import woowacourse.payments.ui.preview.OneCardPreviewParameterProvider

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

@Preview(name = "Empty Card", showBackground = true)
@Composable
private fun EmptyPaymentCardPreview() {
    PaymentCard(
        cardType = CardType.EMPTY,
        onClick = {},
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.content_description_card_list_empty),
            )
        },
        modifier = Modifier.padding(top = 18.dp)
    )
}

@Preview(name = "Registered Card", showBackground = true)
@Composable
private fun RegisteredPaymentCardPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) previewCard: Card
) {
    PaymentCard(
        cardType = CardType.REGISTERED,
        onClick = {},
        content = { RegisteredCard(previewCard) },
        modifier = Modifier
            .padding(top = 18.dp)
            .shadow(8.dp)
    )
}

@Preview(name = "Pending Card", showBackground = true)
@Composable
private fun PendingPaymentCardPreview() {
    PaymentCard(
        cardType = CardType.PENDING,
        onClick = {},
        content = { CardChip() },
        modifier = Modifier
            .padding(top = 18.dp)
            .shadow(8.dp)
    )
}
