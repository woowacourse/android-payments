package woowacourse.payments.ui.screen.cards.component

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import woowacourse.payments.ui.screen.cards.CardsActivity.Companion.EXTRA_CARD
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
    val uiState = stateHolder.uiState
    val context = LocalContext.current
    val launcher = rememberCardAdditionLauncher { newCard -> stateHolder.update(newCard) }

    LaunchedEffect(stateHolder.uiEvent) {
        when (stateHolder.uiEvent) {
            CardsUiEvent.AddCardFailure -> context.showToast(R.string.cards_card_addition_failure)

            CardsUiEvent.AddCardSuccess -> context.showToast(R.string.cards_card_addition_success)

            CardsUiEvent.None -> Unit
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopBar(
                onAddClick = { launcher.launch(CardAdditionActivity.newIntent(context)) },
                isAddButtonVisible = uiState is CardsUiState.MultipleCards,
            )
        },
    ) { paddingValues: PaddingValues ->
        CardsContent(
            state = uiState,
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
private fun rememberCardAdditionLauncher(onCardAdded: (CardUiModel?) -> Unit): ManagedActivityResultLauncher<Intent, ActivityResult> =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val card = result.data?.getParcelableCompat<CardUiModel>(EXTRA_CARD)
            onCardAdded(card)
        }
    }

@Composable
private fun CardsContent(
    state: CardsUiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
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
                )

            is CardsUiState.MultipleCards ->
                MultipleCardsView(cards = state.cards)
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
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExistingCard(card = card)
        Spacer(modifier = Modifier.height(36.dp))
        AddCardButton(onClick = onAddClick)
    }
}

@Composable
private fun MultipleCardsView(
    cards: List<CardUiModel>,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        cards.forEach { card: CardUiModel ->
            ExistingCard(card = card)
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview() {
    CardsScreen()
}
