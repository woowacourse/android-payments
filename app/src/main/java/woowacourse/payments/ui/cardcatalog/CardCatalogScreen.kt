package woowacourse.payments.ui.cardcatalog

import android.app.Activity
import android.content.Intent
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
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardcatalog.component.CardCatalogColumn
import woowacourse.payments.ui.cardcatalog.component.CardCatalogTopBar
import kotlin.jvm.java


@Composable
fun CardCatalogScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val cards = remember { mutableStateListOf<Card>() }

    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard = activityResult.data?.getParcelableExtra<Card>("newCard")
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
            modifier = Modifier.padding(paddingValues)
        )
    }
}


@Preview
@Composable
private fun CardCatalogScreenPreview() {
    CardCatalogScreen()
}
