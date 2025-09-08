package woowacourse.payments.ui.features.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.features.cardlist.components.PaymentsTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardListScreen(onAddCard: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentsTopBar(
                onAddClick = onAddCard,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenPreview() {
    AndroidpaymentsTheme {
        CardListScreen({})
    }
}
