package woowacourse.payments.ui.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    cards: List<CardUiModel> = emptyList(),
    onAddNewCardClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Payments") },
                actions = {
                    if (cards.size > 1) {
                        TextButton(
                            onClick = { onAddNewCardClick() },
                            modifier = Modifier.padding(end = 20.dp),
                        ) {
                            Text(
                                text = "추가",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        }
                    }
                },
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
