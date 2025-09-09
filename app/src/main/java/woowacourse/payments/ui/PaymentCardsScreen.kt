package woowacourse.payments.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.model.Card
import woowacourse.payments.model.EXTRA_CARD
import woowacourse.payments.ui.component.EmptyCard
import woowacourse.payments.ui.component.PaymentCardsTopBar
import woowacourse.payments.ui.component.SingleCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCardsScreen() {
    val context = LocalContext.current
    var cards by rememberSaveable { mutableStateOf(listOf<Card>()) }

    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val newCard = result.data?.getParcelableExtra<Card>(EXTRA_CARD)
                newCard?.let { cards = cards + it }
            }
        }

    Scaffold(
        topBar = {
            PaymentCardsTopBar()
        },
    ) { innerPadding ->
        PaymentCardsContent(
            modifier =
                Modifier
                    .padding(innerPadding),
            cards = cards,
            onAddCard = {
                val intent = Intent(context, AddPaymentCardActivity::class.java)
                cardAddLauncher.launch(intent)
            },
        )
    }
}

@Composable
private fun PaymentCardsContent(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    onAddCard: () -> Unit,
) {
    when (cards.size) {
        0 -> EmptyCard(modifier = modifier, onAddCard = onAddCard)
        1 -> SingleCard(modifier = modifier, card = cards.first(), onAddCard = onAddCard)
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardsPreview() {
    AndroidpaymentsTheme {
        PaymentCardsScreen()
    }
}
