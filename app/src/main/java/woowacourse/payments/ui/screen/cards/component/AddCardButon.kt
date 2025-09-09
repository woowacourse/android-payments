package woowacourse.payments.ui.screen.cards.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.screen.cardAddition.component.PaymentCard
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
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription =
                    stringResource(R.string.cards_card_addition_button_description),
                modifier =
                    Modifier
                        .size(36.dp)
                        .align(Alignment.Center),
                tint = Color.Black,
            )
        },
    )
}

@Preview
@Composable
private fun AddCardButtonPreview() {
    AddCardButton()
}
