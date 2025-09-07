package woowacourse.payments.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.component.CardAdditionTopBar

@Composable
fun CardAdditionScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = {}, onSaveClick = {}
            )
        }) { paddingValues: PaddingValues ->
        CardAdditionColumn(modifier = Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview1() {
    CardAdditionScreen()
}