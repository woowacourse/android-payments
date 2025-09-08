package woowacourse.payments.ui.screen.cards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiState
import woowacourse.payments.ui.screen.cards.CardsUiState

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    cards: List<CardUiModel> = emptyList(),
) {
    val cardsUiState by remember { derivedStateOf { cards.toUiState() } }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopBar(
                onAddClick = onAddClick,
                isAddButtonVisible = cardsUiState is CardsUiState.MultipleCards,
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 16.dp),
        ) {
            when (val state = cardsUiState) {
                CardsUiState.Empty -> {
                    Text(
                        text = stringResource(R.string.cards_card_addition_description),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    AddCardButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = onAddClick,
                    )
                }

                is CardsUiState.SingleCard -> {
                    ExistingCard(
                        card = state.card,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    AddCardButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = onAddClick,
                    )
                }

                is CardsUiState.MultipleCards -> {
                    state.cards.forEach { card: CardUiModel ->
                        ExistingCard(
                            card = card,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )
                        Spacer(modifier = Modifier.height(36.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview() {
    CardsScreen()
}
