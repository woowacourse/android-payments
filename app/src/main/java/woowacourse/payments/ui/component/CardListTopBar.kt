package woowacourse.payments.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text("Payments") },
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
fun CardListTopBarPreview() {
    AndroidpaymentsTheme {
        CardListTopBar()
    }
}
