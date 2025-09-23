package woowacourse.payments.ui.cardlist

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.cardlist.component.CardCatalogColumn
import woowacourse.payments.ui.cardlist.component.CardCatalogTopBar
import woowacourse.payments.ui.cardlist.state.CardListStateHolder
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.state.NewCardStatus


@Composable
fun CardCatalogScreen(
    modifier: Modifier = Modifier
) {
    val stateHolder = CardListStateHolder()

    val context = LocalContext.current

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


    fun  navigateToCreate(){
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
                cardListStatus = stateHolder.uiState,
                onAddCard = {
                    navigateToCreate()
                },
            )
        }
    ) { paddingValues: PaddingValues ->
        CardCatalogColumn(
            cardListStatus = stateHolder.uiState,
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
