package woowacourse.payments.ui.cardcatalog

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardcatalog.component.CardCatalogColumn
import woowacourse.payments.ui.cardcatalog.component.CardCatalogTopBar
import woowacourse.payments.ui.newcard.NewCardActivity


@Composable
fun CardCatalogScreen(
    onEditCard: (Card) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val cards = remember { mutableStateListOf<Card>() }

    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard: Card? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    activityResult.data?.getParcelableExtra("newCard", Card::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    activityResult.data?.getParcelableExtra<Card>("newCard")
                }
                newCard?.let {
                    cards.add(newCard)
                }
            }
        }

    fun openAddCardWithResult() {
        val intent = Intent(context, NewCardActivity::class.java)
        cardAddLauncher.launch(intent)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardCatalogTopBar(
                cardsSize = cards.size,
                onAddNewCardClick = {
                    openAddCardWithResult()
                })
        }
    ) { paddingValues: PaddingValues ->
        CardCatalogColumn(
            cards = cards,
            onClickAddCard = {
                openAddCardWithResult()
            },
            onEditCard = { card -> onEditCard(card) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCatalogScreenPreview() {
    CardCatalogScreen({})
}
