package woowacourse.payments.ui.newcard.component

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import woowacourse.payments.ui.newcard.state.CardStateHolder


@Composable
fun NewCardScreen(
    navigateToBack: () -> Unit,
    onSaveClick: (Card?) -> Unit,
    modifier: Modifier = Modifier
) {
    val stateHolder = CardStateHolder()

    Scaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                onBackClick = { navigateToBack() },
                onSaveClick = { onSaveClick(stateHolder.newCard()) }
            )
        }) { paddingValues: PaddingValues ->
        if (stateHolder.uiState.isBottomSheetOpen) {
            SelectedBankBottomSheet(
                stateHolder = stateHolder,
            )
        }
        NewCardColumn(
            stateHolder = stateHolder,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview1() {
    NewCardScreen({}, {})
}
