package woowacourse.payments.ui.screen.cards.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.screen.cardAddition.component.PaymentCard
import woowacourse.payments.ui.theme.Gray40
import woowacourse.payments.ui.theme.Gray80

@Composable
fun AddCardButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    PaymentCard(
        modifier = modifier.clickable(onClick = onClick),
        backgroundColor = Gray80,
        cardContent = {
            Text(
                text = stringResource(R.string.cards_card_addition_btn),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                color = Gray40,
                fontSize = 34.sp,
            )
        }
    )
}

@Preview
@Composable
private fun AddCardButtonPreview() {
    AddCardButton()
}
