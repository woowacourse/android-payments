package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.CardStateHolder
import woowacourse.payments.ui.newcard.component.NewCardColumn
import woowacourse.payments.ui.newcard.component.NewCardTopBar


@Composable
fun NewCardScreen(
    navigateToBack: () -> Unit,
    onSaveClick: () -> Unit,
    state: CardStateHolder,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                onBackClick = { navigateToBack() },
                onSaveClick = { onSaveClick() }
            )
        }) { paddingValues: PaddingValues ->
        NewCardColumn(
            state = state,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview1() {
    NewCardScreen({}, {}, CardStateHolder())
}
