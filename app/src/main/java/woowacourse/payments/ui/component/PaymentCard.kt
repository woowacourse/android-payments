package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.core.CompanyResourceProvider
import woowacourse.payments.ui.state.BankState
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.theme.Black33
import woowacourse.payments.ui.theme.GrayE5

@Composable
fun PaymentCard(
    resourceProvider: CompanyResourceProvider,
    card: CardState,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: (CardState) -> Unit = {},
    bank: BankState? = null,
) {
    val contentAlignment =
        when (card) {
            CardState.Empty -> Alignment.Center
            is CardState.Pending -> Alignment.CenterStart
            is CardState.Registered -> Alignment.CenterStart
        }

    val backgroundColor =
        bank?.let {
            when (bank) {
                is BankState.Bank -> resourceProvider.getSignatureColor(bank.company)
                BankState.Empty -> Black33
            }
        } ?: GrayE5
    Box(
        contentAlignment = contentAlignment,
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(5.dp),
                ).clickable(onClick = { onClick(card) }),
    ) {
        content()
    }
}
