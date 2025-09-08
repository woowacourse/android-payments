package woowacourse.payments.ui.cardlist

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Payments")
        },
        actions = {
            TextButton(onClick = { onAddClick() }) {
                Text(text = "추가", color = Color.Black)
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CardListTopBarPreview() {
    CardListTopBar(onAddClick = {})
}
