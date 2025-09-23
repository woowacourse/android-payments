package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.common.component.CardChip
import woowacourse.payments.ui.common.component.IssuingBankName
import woowacourse.payments.ui.model.IssuingBank

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    issuingBank: IssuingBank = IssuingBank.NOT_SELECTED,
    cardContent: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .clip(CircleShape.copy(CornerSize(5.dp)))
                .background(color = issuingBank.getColor())
                .padding(12.dp),
    ) {
        IssuingBankName(
            issuingBank = issuingBank,
            modifier = Modifier.align(Alignment.TopStart),
        )
        CardChip()
        cardContent()
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        issuingBank = IssuingBank.KB,
    )
}
