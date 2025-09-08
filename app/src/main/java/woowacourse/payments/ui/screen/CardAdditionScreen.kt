package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.component.cardaddition.CardAdditionColumn
import woowacourse.payments.ui.component.cardaddition.CardAdditionTopBar

@Composable
fun CardAdditionScreen(navigateToBack: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = { navigateToBack() }, onSaveClick = {}
            )
        }) { paddingValues: PaddingValues ->
        CardAdditionColumn(modifier = Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview1() {
    CardAdditionScreen({})
}