package woowacourse.payments.ui.cards.single

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.Card
import woowacourse.payments.ui.cards.CardAction
import woowacourse.payments.ui.cards.CardContent
import woowacourse.payments.ui.cards.CardsActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.cards.CardsScreen
import woowacourse.payments.ui.cards.CardsTopBar
import woowacourse.payments.ui.cards.NewCard
import woowacourse.payments.ui.core.mapper.toCardUiModel
import woowacourse.payments.ui.debug.fixture.cardUiModelSample
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.utils.ext.parcelable

@Composable
fun SingleCardScreen(
    onNavigate: (CardsScreen) -> Unit,
    cardId: Long,
    modifier: Modifier = Modifier
) {
    val cardStateHolder = rememberSaveable(saver = SingleCardStateHolderSaver()) {
        SingleCardStateHolder(cardId)
    }
    val localContext = LocalContext.current
    val cardAddLauncher = cardAddLauncher(cardStateHolder)
    val onAddClick = {
        val intent = NewCardActivity.instance(localContext)
        cardAddLauncher.launch(intent)
    }
    val onUpdateClick: (CardUiModel) -> Unit = {
        val intent = NewCardActivity.instance(localContext, it.id)
        cardAddLauncher.launch(intent)
    }
    handleEvent(cardStateHolder.uiEvent, localContext, onNavigate)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { CardsTopBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            SingleCardsSection(cardStateHolder.card, onAddClick, onUpdateClick)
        }
    }
}

@Composable
fun SingleCardsSection(
    card: CardUiModel,
    onAddClick: () -> Unit,
    onUpdateClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        Spacer(Modifier.height(12.dp))
        Card(
            card,
            Modifier
                .shadow(8.dp)
                .width(width = 208.dp)
                .clickable { onUpdateClick(card) },
        ) {
            CardContent(
                card,
                Modifier
                    .padding(15.dp),
            )
        }
        NewCard(onAddClick)
    }
}

@Composable
private fun cardAddLauncher(
    cardsStateHolder: SingleCardStateHolder,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
) { activityResult ->
    if (activityResult.resultCode == RESULT_OK) {
        val intent = activityResult.data
        val cardAction = intent?.parcelable<CardAction>(
            NEW_CARD_KEY,
        ) ?: return@rememberLauncherForActivityResult
        when (cardAction) {
            is CardAction.Add -> cardsStateHolder.addCard()
            is CardAction.Update -> cardsStateHolder.updateCard()
        }
    }
}

private fun handleEvent(
    uiEvent: SingleCardsUiEvent?,
    localContext: Context,
    updateScreen: (CardsScreen) -> Unit
) {
    when (uiEvent) {
        is SingleCardsUiEvent.AddCard -> {
            updateScreen(CardsScreen.Multi(uiEvent.cards))
            Toast.makeText(
                localContext,
                localContext.getString(R.string.created_card_message),
                Toast.LENGTH_SHORT,
            ).show()
        }

        SingleCardsUiEvent.UpdateCard -> {
            Toast.makeText(
                localContext,
                localContext.getString(R.string.updated_card_message),
                Toast.LENGTH_SHORT,
            ).show()
        }

        null -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun SingleCardsSectionPreview() {
    SingleCardsSection(cardUiModelSample, {}, {})
}
