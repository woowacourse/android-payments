package woowacourse.payments.ui.screen.cards.component

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
fun CardsTopBar(
    modifier: Modifier = Modifier,
    isAddButtonVisible: Boolean = false,
    onAddClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.cards_top_bar_title)) },
        modifier = modifier,
        actions = {
            if (isAddButtonVisible) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = stringResource(R.string.cards_top_bar_add_button),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun CardsTopBarPreview() {
    CardsTopBar(isAddButtonVisible = true)
}
