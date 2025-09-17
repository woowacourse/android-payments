package woowacourse.payments.cards.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray1
import woowacourse.payments.ui.theme.Gray2

@Composable
fun PaymentCardAdditionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardAdditionButtonContentDescription: String =
        stringResource(R.string.cards_top_app_bar_add_action_content_description)
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Gray1,
                    shape = RoundedCornerShape(5.dp),
                ).semantics {
                    contentDescription = cardAdditionButtonContentDescription
                }.clickable {
                    onClick()
                },
    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Gray2),
        )
    }
}

@Preview
@Composable
private fun AddPaymentCardButtonPreview() {
    PaymentCardAdditionButton(
        onClick = { },
    )
}
