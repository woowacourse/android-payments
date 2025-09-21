package woowacourse.payments.ui.cards.component

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray10
import woowacourse.payments.ui.theme.Gray57

@Composable
fun NewCard(
    modifier: Modifier = Modifier,
    onCardAddClick: () -> Unit,
) {
    Box(
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .shadow(8.dp)
                .clickable { onCardAddClick() }
                .background(color = Gray10, shape = RoundedCornerShape(5.dp)),
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            tint = Gray57,
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.card_register),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardPreview() {
    NewCard { }
}
