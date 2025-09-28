package woowacourse.payments.ui.screen.cards.component

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.common.extension.getParcelableCompat
import woowacourse.payments.ui.common.extension.showToast
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.CardAdditionActivity
import woowacourse.payments.ui.screen.cardAddition.CardAdditionActivity.Companion.EXTRA_NEW_CARD
import woowacourse.payments.ui.screen.cards.CardsUiEvent
import woowacourse.payments.ui.screen.cards.CardsUiState
import woowacourse.payments.ui.screen.cards.CardsUiStateHolder

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    initialState: CardsUiState = CardsUiState.Empty,
) {
    val stateHolder =
        rememberSaveable(saver = CardsUiStateHolder.Saver) { CardsUiStateHolder(initialState) }
    val context = LocalContext.current
    val addCardLauncher = rememberCardLauncher(stateHolder::update)
    val editCardLauncher = rememberCardLauncher(stateHolder::replaceCard)

    LaunchedEffect(stateHolder.uiEvent) {
        when (stateHolder.uiEvent) {
            CardsUiEvent.AddCardFailure -> context.showToast(R.string.cards_card_addition_failure)
            CardsUiEvent.AddCardSuccess -> context.showToast(R.string.cards_card_addition_success)
            CardsUiEvent.EditCardFailure -> context.showToast(R.string.cards_card_edit_failure)
            CardsUiEvent.EditCardSuccess -> context.showToast(R.string.cards_card_edit_success)
            CardsUiEvent.None -> Unit
        }
        stateHolder.consumeEvent()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopBar(
                onAddClick = { addCardLauncher.launch(CardAdditionActivity.newIntent(context)) },
                isAddButtonVisible = stateHolder.uiState is CardsUiState.MultipleCards,
            )
        },
    ) { paddingValues: PaddingValues ->
        CardsContent(
            state = stateHolder.uiState,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 16.dp),
            onAddClick = { addCardLauncher.launch(CardAdditionActivity.newIntent(context)) },
            onCardClick = { card ->
                stateHolder.markEditCard(card)
                editCardLauncher.launch(CardAdditionActivity.newIntentForEdit(context, card))
            },
        )
    }
}

@Composable
private fun rememberCardLauncher(onNewCard: (CardUiModel?) -> Unit): ManagedActivityResultLauncher<Intent, ActivityResult> =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val card = result.data?.getParcelableCompat<CardUiModel>(EXTRA_NEW_CARD)
            onNewCard(card)
        }
    }

@Composable
private fun CardsContent(
    state: CardsUiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    onCardClick: (CardUiModel) -> Unit = {},
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        when (state) {
            CardsUiState.Empty ->
                EmptyView(onAddClick = onAddClick)

            is CardsUiState.SingleCard ->
                SingleCardView(
                    card = state.card,
                    onAddClick = onAddClick,
                    onCardClick = onCardClick,
                )

            is CardsUiState.MultipleCards ->
                MultipleCardsView(
                    cards = state.cards,
                    onCardClick = onCardClick,
                )
        }
    }
}

@Composable
private fun EmptyView(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.cards_card_addition_notice),
            modifier =
                Modifier.semantics {
                    contentDescription = context.getString(R.string.cards_card_addition_description)
                },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(32.dp))
        AddCardButton(onClick = onAddClick)
    }
}

@Composable
private fun SingleCardView(
    card: CardUiModel,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    onCardClick: (CardUiModel) -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExistingCard(
            card = card,
            onClick = { onCardClick(card) },
        )
        Spacer(modifier = Modifier.height(36.dp))
        AddCardButton(onClick = onAddClick)
    }
}

@Composable
private fun MultipleCardsView(
    cards: List<CardUiModel>,
    modifier: Modifier = Modifier,
    onCardClick: (CardUiModel) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        items(cards) { card ->
            ExistingCard(
                card = card,
                onClick = { onCardClick(card) },
            )
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview() {
    CardsScreen()
}
