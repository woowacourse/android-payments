package woowacourse.payments.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentToolbar(
    onAddClick: () -> Unit,
    addButtonVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_list_title),
                fontSize = 20.sp,
                color = Color.Black,
            )
        },
        actions = {
            if (addButtonVisible) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = stringResource(R.string.card_list_add),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentToolbarPreview() {
    PaymentToolbar(
        addButtonVisible = true,
        onAddClick = {},
    )
}
