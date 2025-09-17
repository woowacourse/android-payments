package woowacourse.payments.cards.component

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.BankType
import woowacourse.payments.Card
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.R
import woowacourse.payments.cardaddition.CardAdditionActivity
import woowacourse.payments.cards.CardsStateHolder
import woowacourse.payments.cards.CardsUiEvent
import woowacourse.payments.cards.CardsUiState
import woowacourse.payments.getParcelableCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    stateHolder: CardsStateHolder = rememberSaveable(saver = CardsStateHolder.Saver) { CardsStateHolder() },
) {
    val state: CardsUiState = stateHolder.uiState
    var event by remember { mutableStateOf<CardsUiEvent?>(null) }

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val cardAddLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val card: Card =
                    result.data?.getParcelableCompat(EXTRA_CARD)
                        ?: return@rememberLauncherForActivityResult
                stateHolder.addCard(card)
                event = CardsUiEvent.AddCardSuccess
            }
        }
    val navigateToCardAdditionActivity: () -> Unit =
        { cardAddLauncher.launch(Intent(context, CardAdditionActivity::class.java)) }

    LaunchedEffect(event) {
        when (event) {
            CardsUiEvent.AddCardSuccess -> {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.cards_add_card_success_message),
                        Toast.LENGTH_SHORT,
                    ).show()
            }

            null -> Unit
        }
        event = null
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopAppBar(
                addCardAction = if (state.cards.size > 1) navigateToCardAdditionActivity else null,
            )
        },
    ) { innerPadding: PaddingValues ->
        CardsContent(
            state = state,
            navigateToCardAdditionActivity = navigateToCardAdditionActivity,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
        )
    }
}

@Composable
private fun CardsContent(
    state: CardsUiState,
    navigateToCardAdditionActivity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        when (state.cards.size) {
            0 ->
                NoCardContent(
                    addCard = navigateToCardAdditionActivity,
                    modifier = Modifier.fillMaxSize(),
                )

            1 ->
                OneCardContent(
                    card = state.cards.first(),
                    addCard = navigateToCardAdditionActivity,
                    modifier = Modifier.fillMaxSize(),
                )

            else ->
                MultipleCardContent(
                    cards = state.cards,
                    modifier = Modifier.fillMaxSize(),
                )
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: List<Card>,
) {
    AndroidpaymentsTheme {
        CardsScreen(
            stateHolder = CardsStateHolder(CardsUiState(cards)),
        )
    }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<List<Card>> {
    override val values: Sequence<List<Card>> =
        sequenceOf(
            emptyList(),
            listOf(
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.BC,
                ),
            ),
            listOf(
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    BankType.KB,
                ),
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    BankType.HANA,
                ),
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    BankType.KAKAO,
                ),
            ),
        )
}
