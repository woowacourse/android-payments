package woowacourse.payments.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.LightGray

@Composable
fun AddNewCard(onAddClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .height(124.dp)
                .width(208.dp)
                .clickable(onClick = onAddClick)
                .background(color = LightGray),
        content = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.content_description_add_new_card),
                modifier = Modifier.align(alignment = Alignment.Center),
            )
        },
    )
}
