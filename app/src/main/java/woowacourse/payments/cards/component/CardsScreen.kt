package woowacourse.payments.cards.component

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.BankType
import woowacourse.payments.CardUiModel
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.EXTRA_NEW_CARD
import woowacourse.payments.EXTRA_OLD_CARD
import woowacourse.payments.R
import woowacourse.payments.cardaddition.CardAdditionActivity
import woowacourse.payments.cardediting.CardEditingActivity
import woowacourse.payments.cards.CardsStateHolder
import woowacourse.payments.cards.CardsUiEvent
import woowacourse.payments.cards.CardsUiState
import woowacourse.payments.cards.rememberCardsStateHolder
import woowacourse.payments.getParcelableExtraCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    stateHolder: CardsStateHolder = rememberCardsStateHolder(),
) {
    val state: CardsUiState = stateHolder.uiState
    val event: CardsUiEvent? = stateHolder.event

    val context = LocalContext.current

    val cardAddLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val card: CardUiModel =
                    result.data?.getParcelableExtraCompat(EXTRA_CARD)
                        ?: return@rememberLauncherForActivityResult

                stateHolder.addCard(card)
            }
        }

    val navigateToCardAdditionActivity: () -> Unit =
        { cardAddLauncher.launch(Intent(context, CardAdditionActivity::class.java)) }

    val cardEditLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val old: CardUiModel =
                    result.data?.getParcelableExtraCompat(EXTRA_OLD_CARD)
                        ?: return@rememberLauncherForActivityResult

                val new: CardUiModel =
                    result.data?.getParcelableExtraCompat(EXTRA_NEW_CARD)
                        ?: return@rememberLauncherForActivityResult

                stateHolder.editCard(
                    old = old,
                    new = new,
                )
            }
        }

    val navigateToEditingActivity: (card: CardUiModel) -> Unit =
        { card ->
            cardEditLauncher.launch(
                Intent(context, CardEditingActivity::class.java).putExtra(
                    EXTRA_CARD,
                    card,
                ),
            )
        }

    LaunchedEffect(event) {
        when (event) {
            CardsUiEvent.AddCardSuccess -> {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.cards_add_card_success_message),
                        Toast.LENGTH_SHORT,
                    ).show()

                stateHolder.fetchCards()
            }

            CardsUiEvent.EditCardSuccess -> {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.cards_edit_card_success_message),
                        Toast.LENGTH_SHORT,
                    ).show()

                stateHolder.fetchCards()
            }

            null -> Unit
        }

        stateHolder.clearEvent()
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
            navigateToCardEditingActivity = navigateToEditingActivity,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        )
    }
}

@Composable
private fun CardsContent(
    state: CardsUiState,
    navigateToCardAdditionActivity: () -> Unit,
    navigateToCardEditingActivity: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Column(
        modifier = modifier,
    ) {
        when (state.cards.size) {
            0 ->
                NoCardContent(
                    addCard = navigateToCardAdditionActivity,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                )

            1 ->
                OneCardContent(
                    card = state.cards.first(),
                    addCard = navigateToCardAdditionActivity,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                    onClickCard = navigateToCardEditingActivity,
                )

            else ->
                MultipleCardContent(
                    cards = state.cards,
                    onClickCard = navigateToCardEditingActivity,
                    modifier = Modifier.fillMaxSize(),
                )
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: List<CardUiModel>,
) {
    AndroidpaymentsTheme {
        CardsScreen(
            stateHolder = CardsStateHolder(CardsUiState(cards)),
        )
    }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<List<CardUiModel>> {
    override val values: Sequence<List<CardUiModel>> =
        sequenceOf(
            emptyList(),
            listOf(
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.BC,
                ),
            ),
            listOf(
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.KB,
                ),
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.HANA,
                ),
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.KAKAO,
                ),
            ),
        )
}
