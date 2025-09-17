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
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.cards.CardsActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.cards.model.CardsState
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.utils.ext.parcelable

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
) {
    val cardsStateHolder = rememberSaveable(saver = CardsStateHolderSaver()) {
        CardsStateHolder(CardsState.of(emptyList()))
    }
    val localContext = LocalContext.current
    val cardAddLauncher = cardAddLauncher(cardsStateHolder, localContext)
    val onAddClick = {
        val intent = NewCardActivity.instance(localContext)
        cardAddLauncher.launch(intent)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CardsTopBar(
                cardsState = cardsStateHolder.cardsState,
                onAddClick = onAddClick
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            when (val cardUiState = cardsStateHolder.cardsState) {
                CardsState.None -> NonCardsSection(onAddClick)
                is CardsState.Single -> SingleCardsSection(onAddClick, cardUiState.card)
                is CardsState.Multiple -> MultiCardsSection(cardUiState.cards)
            }
        }
    }
}

@Composable
fun cardAddLauncher(
    cardsStateHolder: CardsStateHolder,
    context: Context,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
) { activityResult ->
    if (activityResult.resultCode == RESULT_OK) {
        val intent = activityResult.data
        val cardUiModel =
            intent?.parcelable<PaymentCardUiModel>(
                NEW_CARD_KEY,
            ) ?: return@rememberLauncherForActivityResult
        cardsStateHolder.addCard(cardUiModel)
        Toast
            .makeText(
                context,
                context.getString(R.string.created_card_message),
                Toast.LENGTH_SHORT,
            ).show()
    }
}

@Preview(showBackground = true)
@Composable
fun CardsScreenPreview() {
    CardsScreen()
}
