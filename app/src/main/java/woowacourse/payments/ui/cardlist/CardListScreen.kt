package woowacourse.payments.ui.cardlist

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardlist.component.CardCatalogColumn
import woowacourse.payments.ui.cardlist.component.CardCatalogTopBar
import woowacourse.payments.ui.cardlist.state.CardListStateHolder
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity


@Composable
fun CardCatalogScreen(
    onEditCard: (CardUiModel) -> Unit,
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

    fun navigateToCardList() {
        val intent = Intent(context, NewCardActivity::class.java)
        cardAddLauncher.launch(intent)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardCatalogTopBar(
                cardListStatus = stateHolder.uiState,
                onAddCard = {
                    navigateToCardList()
                },
            )
        }
    ) { paddingValues: PaddingValues ->
        CardCatalogColumn(
            cardListStatus = stateHolder.uiState,
            onAddCard = {
                navigateToCardList()
            },
            onEditCard = { cardUiModel ->
                onEditCard(cardUiModel)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCatalogScreenPreview() {
    CardCatalogScreen({})
}
