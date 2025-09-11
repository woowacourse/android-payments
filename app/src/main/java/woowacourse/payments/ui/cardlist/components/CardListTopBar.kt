package woowacourse.payments.ui.cardlist.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    showAddButton: Boolean = false,
) {
    val addCardDescription: String = stringResource(R.string.add_button_description)

    CenterAlignedTopAppBar(
        title = {
            Text(text = "Payments")
        },
        actions = {
            if (showAddButton) {
                TextButton(
                    onClick = { onAddClick() },
                    modifier = modifier.semantics { contentDescription = addCardDescription },
                ) {
                    Text(text = stringResource(R.string.add), color = Color.Black)
                }
            }
        },
        modifier = modifier,
    )
}

@Preview(name = "카드 추가 없음")
@Composable
private fun CardListTopBarPreview1() {
    CardListTopBar(showAddButton = false)
}

@Preview("카드 추가 있음")
@Composable
private fun CardListTopBarPreview2() {
    CardListTopBar(showAddButton = true)
}
