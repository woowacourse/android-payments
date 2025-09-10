package woowacourse.payments.ui.screen.cards.component

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.common.extension.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.CardAdditionActivity
import woowacourse.payments.ui.screen.cards.CardsActivity.Companion.EXTRA_CARD
import woowacourse.payments.ui.screen.cards.CardsUiState

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    initialState: CardsUiState = CardsUiState.Empty,
) {
    var cardsUiState by rememberSaveable { mutableStateOf(initialState) }
    val context = LocalContext.current
    val launcher =
        rememberCardAdditionLauncher { newCard ->
            cardsUiState = cardsUiState.addCard(newCard)
        }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopBar(
                onAddClick = { launcher.launch(CardAdditionActivity.newIntent(context)) },
                isAddButtonVisible = cardsUiState is CardsUiState.MultipleCards,
            )
        },
    ) { paddingValues: PaddingValues ->
        CardsContent(
            state = cardsUiState,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 16.dp),
            onAddClick = { launcher.launch(CardAdditionActivity.newIntent(context)) },
        )
    }
}

@Composable
private fun rememberCardAdditionLauncher(onCardAdded: (CardUiModel) -> Unit): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val card = result.data?.getParcelableCompat<CardUiModel>(EXTRA_CARD)
            card?.let { card ->
                onCardAdded(card)
                Toast
                    .makeText(
                        context,
                        R.string.cards_card_addition_success,
                        Toast.LENGTH_SHORT,
                    ).show()
            }
        }
    }
}

@Composable
private fun CardsContent(
    state: CardsUiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        when (state) {
            CardsUiState.Empty ->
                EmptyView(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onAddClick = onAddClick,
                )

            is CardsUiState.SingleCard ->
                SingleCardView(
                    card = state.card,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onAddClick = onAddClick,
                )

            is CardsUiState.MultipleCards ->
                MultipleCardsView(
                    cards = state.cards,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
        }
    }
}

@Composable
private fun EmptyView(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
) {
    Text(
        text = stringResource(R.string.cards_card_addition_description),
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = modifier.height(32.dp))
    AddCardButton(
        modifier = modifier,
        onClick = onAddClick,
    )
}

@Composable
private fun SingleCardView(
    card: CardUiModel,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
) {
    ExistingCard(
        card = card,
        modifier = modifier,
    )
    Spacer(modifier = modifier.height(36.dp))
    AddCardButton(
        modifier = modifier,
        onClick = onAddClick,
    )
}

@Composable
private fun MultipleCardsView(
    cards: List<CardUiModel>,
    modifier: Modifier = Modifier,
) {
    cards.forEach { card: CardUiModel ->
        ExistingCard(
            card = card,
            modifier = modifier,
        )
        Spacer(modifier = modifier.height(36.dp))
    }
}

@Preview
@Composable
private fun CardsScreenPreview() {
    CardsScreen()
}
