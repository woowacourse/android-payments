package woowacourse.payments.ui.cardlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.cardlist.component.CardCatalogColumn
import woowacourse.payments.ui.cardlist.component.CardCatalogTopBar
import woowacourse.payments.ui.cardlist.state.CardListStateHolder
import woowacourse.payments.ui.model.CardUiModel


@Composable
fun CardCatalogScreen(
    onAddCard: () -> Unit,
    onEditCard: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val stateHolder = CardListStateHolder()

    Scaffold(
        modifier = modifier,
        topBar = {
            CardCatalogTopBar(
                cardListStatus = stateHolder.uiState,
                onAddCard = { onAddCard() },
            )
        }
    ) { paddingValues: PaddingValues ->
        CardCatalogColumn(
            cardListStatus = stateHolder.uiState,
            onAddCard = { onAddCard() },
            onEditCard = { cardUiModel -> onEditCard(cardUiModel) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCatalogScreenPreview() {
    CardCatalogScreen({}, {})
}
