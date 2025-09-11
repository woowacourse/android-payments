package woowacourse.payments.ui.allcards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.allcards.component.EmptyCard
import woowacourse.payments.ui.allcards.component.MultipleCards
import woowacourse.payments.ui.allcards.component.PlusCard
import woowacourse.payments.ui.allcards.component.SingleCard
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState

@Composable
fun AllCardsScreen(
    allCards: AllCardsUiState,
    modifier: Modifier = Modifier,
    onPlusCardClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (allCards.viewType) {
            AllCardsUiState.ViewType.EMPTY -> EmptyCard(onPlusCardClick)
            AllCardsUiState.ViewType.SINGLE -> SingleCard(allCards.cards, onPlusCardClick)
            AllCardsUiState.ViewType.MULTIPLE -> MultipleCards(allCards.cards)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllCardsScreenPreview() {
    AndroidpaymentsTheme {
        val cards = rememberSaveable { mutableStateListOf<CardInfoUiState>() }
        Scaffold(
            topBar = {
                AllCardsTopbar(
                    allCards = AllCardsUiState(cards),
                )
            },
        ) {
            AllCardsScreen(
                allCards = AllCardsUiState(cards),
                modifier = Modifier.padding(it),
            )
        }
    }
}
