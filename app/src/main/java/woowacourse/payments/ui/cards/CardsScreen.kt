package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.cards.components.Cards
import woowacourse.payments.ui.cards.components.CardsTopBar
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun CardsScreen(
    cardsStateHolder: CardsStateHolder = remember { CardsStateHolder() },
    onAddClick: () -> Unit = {},
    onEditClick: (PaymentCardUiModel) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CardsTopBar(
                onAddClick = onAddClick,
                isAddable = cardsStateHolder.isAddableWithTopBar(),
            )
        },
    ) { innerPadding ->
        Cards(
            cardsStateHolder = cardsStateHolder,
            scrollState = scrollState,
            onAddClick = onAddClick,
            onEditClick = onEditClick,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    CardsScreen()
}
