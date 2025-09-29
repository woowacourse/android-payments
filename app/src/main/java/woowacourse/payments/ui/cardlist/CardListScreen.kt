package woowacourse.payments.ui.cardlist

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.cardlist.component.CardCatalogColumn
import woowacourse.payments.ui.cardlist.component.CardCatalogTopBar
import woowacourse.payments.ui.cardlist.state.CardListStateHolder
import woowacourse.payments.ui.cardlist.state.CardListUiStatus
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.state.NewCardStatus


@Composable
fun CardCatalogScreen(
    modifier: Modifier = Modifier
) {
    val stateHolder = rememberSaveable { CardListStateHolder() }

    val context = LocalContext.current

    val cards by stateHolder.cards

    val cardListUiStatus by remember(cards) {
        derivedStateOf {
            when (cards.size) {
                0 -> CardListUiStatus.EmptyCardList
                1 -> CardListUiStatus.OneCardList(cards.first())
                else -> CardListUiStatus.MultiCardList(cards)
            }
        }
    }


    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard: CardUiModel =
                    activityResult.data?.getParcelableCompat("newCard")
                        ?: return@rememberLauncherForActivityResult
                val oldCard: CardUiModel? = activityResult.data?.getParcelableCompat("oldCard")

                if (oldCard != null) {
                    stateHolder.replaceCard(oldCard, newCard)
                } else {
                    stateHolder.addCard(newCard)
                }
            }
        }


    fun navigateToCreate() {
        val intent = NewCardActivity.Intent(context, NewCardStatus.CreateCard)
        cardAddLauncher.launch(intent)
    }

    fun navigateToEdit(card: CardUiModel) {
        val intent = NewCardActivity.Intent(context, NewCardStatus.EditCard(card))
        cardAddLauncher.launch(intent)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardCatalogTopBar(
                cardListStatus = cardListUiStatus,
                onAddCard = {
                    navigateToCreate()
                },
            )
        }
    ) { paddingValues: PaddingValues ->
        CardCatalogColumn(
            cardListStatus = cardListUiStatus,
            onAddCard = {
                navigateToCreate()
            },
            onEditCard = { cardUiModel ->
                navigateToEdit(cardUiModel)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCatalogScreenPreview() {
    CardCatalogScreen()
}
