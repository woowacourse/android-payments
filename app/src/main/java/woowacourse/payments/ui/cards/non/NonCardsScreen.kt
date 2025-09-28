package woowacourse.payments.ui.cards.non

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.CardAction
import woowacourse.payments.ui.cards.CardsActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.cards.CardsScreen
import woowacourse.payments.ui.cards.CardsTopBar
import woowacourse.payments.ui.cards.NewCard
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.utils.ext.parcelable

@Composable
fun NonCardsScreen(
    onNavigate: (CardsScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardStateHolder = remember { NonCardStateHolder() }
    val localContext = LocalContext.current
    val cardAddLauncher = cardAddLauncher(cardStateHolder)
    val onAddClick = {
        val intent = NewCardActivity.instance(localContext)
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
            NonCardsSection(onAddClick)
        }
    }
}

@Composable
fun NonCardsSection(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier,
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            stringResource(R.string.guide_add_new_card),
            style =
                MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                ),
            textAlign = TextAlign.Center,
        )
        NewCard(onAddClick)
    }
}

@Composable
private fun cardAddLauncher(
    cardsStateHolder: NonCardStateHolder,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
) { activityResult ->
    if (activityResult.resultCode == RESULT_OK) {
        val intent = activityResult.data
        val cardAction = intent?.parcelable<CardAction>(
            NEW_CARD_KEY,
        ) ?: return@rememberLauncherForActivityResult
        when (cardAction) {
            is CardAction.Add -> cardsStateHolder.addCard(cardAction.cardId)
            is CardAction.Update -> {}
        }
    }
}

private fun handleEvent(
    uiEvent: NonCardsUiEvent?,
    localContext: Context,
    updateScreen: (CardsScreen) -> Unit
) {
    when (uiEvent) {
        null -> {}
        is NonCardsUiEvent.AddedCard -> {
            updateScreen(CardsScreen.Single(uiEvent.cardId))
            Toast.makeText(
                localContext,
                localContext.getString(R.string.created_card_message),
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}

@Preview
@Composable
fun NonCardsSectionPreview() {
    NonCardsSection({})
}
