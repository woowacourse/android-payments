package woowacourse.payments.card.add

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.card.add.components.NewCardTopBar

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding: PaddingValues ->
        NewCardTopBar(
            onBackClick = onBackClick,
            onSaveClick = onSaveClick,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview
@Composable
fun NewCardScreenPreview() {
    NewCardScreen()
}
