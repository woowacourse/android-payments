package woowacourse.payments.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cards.component.NewCard
import woowacourse.payments.ui.cards.component.RegisteredCards
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    stateHolder: CardsStateHolder,
    onCardAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CardsTopBar(
                onCardAddClick = onCardAddClick,
                isRegisterCardButtonVisible = stateHolder.isRegisterCardButtonVisible,
            )
        },
        content = { innerPadding ->
            CardsScreenContent(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                onCardAddClick = onCardAddClick,
                isCardRegisterMessageVisible = stateHolder.isCardRegisterMessageVisible,
                isRegisteredCardsVisible = stateHolder.isRegisteredCardsVisible,
                isNewCardVisible = stateHolder.isNewCardVisible,
                cardsState = stateHolder.cardsState,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardsTopBar(
    modifier: Modifier = Modifier,
    isRegisterCardButtonVisible: Boolean,
    onCardAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(stringResource(R.string.card_top_bar_title)) },
        actions = {
            if (isRegisterCardButtonVisible) {
                Text(
                    modifier =
                        Modifier
                            .clickable { onCardAddClick() }
                            .padding(end = 20.dp),
                    text = stringResource(R.string.register_card_button),
                )
            }
        },
    )
}

@Composable
private fun CardsScreenContent(
    modifier: Modifier = Modifier,
    onCardAddClick: () -> Unit,
    isCardRegisterMessageVisible: Boolean,
    isRegisteredCardsVisible: Boolean,
    isNewCardVisible: Boolean,
    cardsState: SnapshotStateList<Card>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (isCardRegisterMessageVisible) {
            Text(
                modifier = Modifier.padding(vertical = 32.dp),
                text = stringResource(R.string.card_register_message),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        if (isRegisteredCardsVisible) RegisteredCards(cards = cardsState)

        if (isNewCardVisible) NewCard(onCardAddClick = onCardAddClick)
    }
}

@Preview
@Composable
private fun CardScreenPreview() {
    AndroidpaymentsTheme {
        CardsScreen(stateHolder = CardsStateHolder(), onCardAddClick = {})
    }
}
