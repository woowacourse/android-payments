package woowacourse.payments.ui.allcards.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@Composable
fun PlusCard(
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .height(124.dp)
            .width(208.dp)
            .background(
                color = colorResource(id = R.color.payments_plus_card_background),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.payments_allcards_topbar_add_cards),
            tint = colorResource(id = R.color.payments_plus_card_icon_color),
        )
    }
}