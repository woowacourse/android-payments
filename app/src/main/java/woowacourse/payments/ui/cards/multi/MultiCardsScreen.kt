package woowacourse.payments.ui.cards.multi

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.Card
import woowacourse.payments.ui.cards.CardAction
import woowacourse.payments.ui.cards.CardContent
import woowacourse.payments.ui.cards.CardsActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.cards.CardsTopBar
import woowacourse.payments.ui.debug.fixture.cardUiModelSamples
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.utils.ext.parcelable

@Composable
fun MultiCardsScreen(
    cardIds: List<Long>,
    modifier: Modifier = Modifier,
) {
    val cardStateHolder =
        rememberSaveable(saver = MultiCardsStateHolderSaver()) {
            MultiCardsStateHolder(cardIds)
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
    handleEvent(cardStateHolder.uiEvent, localContext)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CardsTopBar {
                CreateCardButton(onAddClick)
            }
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            MultiCardsSection(cardStateHolder.cards, onUpdateClick)
        }
    }
}

@Composable
fun MultiCardsSection(
    cards: List<CardUiModel>,
    onUpdateClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        items(cards) { card ->
            Card(
                card,
                modifier
                    .shadow(8.dp)
                    .width(width = 208.dp)
                    .clickable { onUpdateClick(card) },
            ) {
                CardContent(
                    card,
                    Modifier.padding(15.dp),
                )
            }
        }
    }
}

@Composable
private fun CreateCardButton(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(onClick = onAddClick, modifier) {
        Text(
            stringResource(R.string.add_message),
            color = Color.Black,
            style =
                MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                ),
        )
    }
}

@Composable
private fun cardAddLauncher(cardsStateHolder: MultiCardsStateHolder) =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            val intent = activityResult.data
            val cardAction =
                intent?.parcelable<CardAction>(
                    NEW_CARD_KEY,
                ) ?: return@rememberLauncherForActivityResult
            when (cardAction) {
                is CardAction.Add -> cardsStateHolder.addCard(cardAction.cardId)
                is CardAction.Update -> cardsStateHolder.updateCard()
            }
        }
    }

private fun handleEvent(
    uiEvent: MultiCardsUiEvent?,
    localContext: Context,
) {
    when (uiEvent) {
        null -> {}
        MultiCardsUiEvent.AddCard ->
            Toast
                .makeText(
                    localContext,
                    localContext.getString(R.string.created_card_message),
                    Toast.LENGTH_SHORT,
                ).show()

        MultiCardsUiEvent.UpdateCard ->
            Toast
                .makeText(
                    localContext,
                    localContext.getString(R.string.updated_card_message),
                    Toast.LENGTH_SHORT,
                ).show()
    }
}

@Preview
@Composable
fun MultiCardsSectionPreview() {
    MultiCardsSection(
        cardUiModelSamples,
        {},
    )
}
