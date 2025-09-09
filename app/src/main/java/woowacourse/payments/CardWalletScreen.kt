package woowacourse.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.component.CardWalletTopBar

@Composable
fun CardWalletScreen(
) {

    Scaffold(
        topBar = {
            CardWalletTopBar(

            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardWalletScreenPreview() {
    CardWalletScreen()
}
