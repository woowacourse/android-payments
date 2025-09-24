package woowacourse.payments.ui.cards

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.cards.CardsActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.cards.model.CardsUiEvent
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.utils.ext.parcelable

@Composable
fun CardsScreen(modifier: Modifier = Modifier) {
    val cardsUiStateHolder = rememberSaveable(saver = CardsStateHolderSaver()) {
        CardsStateHolder(emptyList())
    }
    val localContext = LocalContext.current
    val cardAddLauncher = cardAddLauncher(cardsUiStateHolder)
    val onAddClick = {
        val intent = NewCardActivity.instance(localContext)
        cardAddLauncher.launch(intent)
    }
    val onUpdateClick: (CardUiModel) -> Unit = {
        val intent = NewCardActivity.instance(localContext, it)
        cardAddLauncher.launch(intent)
    }
    handleEvent(cardsUiStateHolder.cardsUiEvent, localContext)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CardsTopBar(
                cardsUiState = cardsUiStateHolder.cardsUiState,
                onAddClick = onAddClick,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            when (val cardsUiState = cardsUiStateHolder.cardsUiState) {
                is CardsUiState.Success -> {
                    when (val content = cardsUiState.content) {
                        is CardsUiState.Success.Content.Multiple -> MultiCardsSection(
                            content.cards,
                            onUpdateClick
                        )

                        CardsUiState.Success.Content.None -> NonCardsSection(onAddClick)
                        is CardsUiState.Success.Content.Single -> SingleCardsSection(
                            content.card,
                            onAddClick,
                            onUpdateClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun cardAddLauncher(
    cardsStateHolder: CardsStateHolder,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
) { activityResult ->
    if (activityResult.resultCode == RESULT_OK) {
        val intent = activityResult.data
        val cardUiModel = intent?.parcelable<CardUiModel>(
            NEW_CARD_KEY,
        ) ?: return@rememberLauncherForActivityResult
        cardsStateHolder.upsertCard(cardUiModel)
    }
}

fun handleEvent(uiEvent: CardsUiEvent?, localContext: Context) {
    when (uiEvent) {
        CardsUiEvent.AddCard -> {
            Toast.makeText(
                localContext,
                localContext.getString(R.string.created_card_message),
                Toast.LENGTH_SHORT,
            ).show()
        }

        CardsUiEvent.UpdateCard -> {
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
fun CardsScreenPreview() {
    CardsScreen()
}
