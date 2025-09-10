package woowacourse.payments.ui.cardlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@Composable
fun AddPaymentCard(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    val addCardDescription: String = stringResource(R.string.add_card_button_description)

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp),
                )
                .clickable { onAddClick() }
                .semantics { contentDescription = addCardDescription },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = Color.DarkGray,
        )
    }
}

@Preview
@Composable
private fun AddPaymentCardPreview() {
    AddPaymentCard()
}
