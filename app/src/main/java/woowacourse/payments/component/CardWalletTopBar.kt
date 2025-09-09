package woowacourse.payments.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWalletTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text("Payments") },
    )
}

@Composable
@Preview(showBackground = true)
fun CardWalletTopBarPreview() {
    CardWalletTopBar()
}
