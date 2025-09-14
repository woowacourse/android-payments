package woowacourse.payments.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.newCard.CardScreenUiState

@Composable
fun CardListScreen(
    cards: CardScreenUiState,
    onAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                actions = {
                    if (cards is CardScreenUiState.MultipleCard) {
                        TextButton(
                            onClick = onAddClick,
                            content = {
                                Text(
                                    text = stringResource(R.string.add_new_card_button),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                )
                            },
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            when (cards) {
                is CardScreenUiState.Empty -> {
                    AddNewCardText()
                    AddNewCard(onAddClick = onAddClick)
                }

                is CardScreenUiState.SingleCard -> {
                    CardList(listOf(cards.card))
                    AddNewCard(onAddClick = onAddClick)
                }

                is CardScreenUiState.MultipleCard -> {
                    CardList(cards.cards)
                }
            }
        }
    }
}
