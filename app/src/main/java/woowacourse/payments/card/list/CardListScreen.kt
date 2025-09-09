package woowacourse.payments.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.CardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CardListScreen(
    cards: List<CardUiModel> = emptyList(),
    onAddNewCardClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Payments") },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (cards.size) {
                0 -> {
                    NoCardScreen(onAddNewCardClick = onAddNewCardClick)
                }

                1 -> {
                    OneCardScreen(card = cards.first(), onAddNewCardClick = onAddNewCardClick)
                }

                else -> {
                    MultipleCardsScreen(cards = cards, onAddNewCardClick = onAddNewCardClick)
                }
            }
        }
    }
}