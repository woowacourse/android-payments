package woowacourse.payments.ui.cardlist

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.cardlist.components.CardListTopBar
import woowacourse.payments.ui.common.components.PaymentCard
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.util.getParcelableCompat

@Composable
fun CardListScreen(
    cards: List<CardUiModel> = emptyList(),
    onCardAdded: (CardUiModel) -> Unit = {},
) {
    val context = LocalContext.current
    val newCardLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data
                    ?.getParcelableCompat<CardUiModel>(NewCardActivity.INTENT_NEW_CARD_KEY)
                    ?.let(onCardAdded)
            }
        }
    val launchNewCard: () -> Unit = { newCardLauncher.launch(NewCardActivity.newIntent(context)) }

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = launchNewCard,
                showAddButton = cards.size > 1,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding() + 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            if (cards.isEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.add_card_message),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            cards.forEach { card: CardUiModel ->
                PaymentCard(card = card)
            }
            if (cards.size <= 1) {
                AddPaymentCard(onAddClick = launchNewCard)
            }
        }
    }
}

@Preview(name = "카드 0개")
@Composable
private fun CardListScreenPreview1() {
    CardListScreen()
}

@Preview(name = "카드 1개")
@Composable
private fun CardListScreenPreview2() {
    CardListScreen(
        cards =
            listOf(
                CardUiModel(
                    number = "1111 - 2222 - 3333 - 4444",
                    expirationDate = "09 / 25",
                    holderName = "CREW",
                ),
            ),
    )
}

@Preview(name = "카드 n개")
@Composable
private fun CardListScreenPreview3() {
    CardListScreen(
        cards =
            List(3) {
                CardUiModel(
                    number = "1111 - 2222 - 3333 - 4444",
                    expirationDate = "09 / 25",
                    holderName = "CREW",
                )
            },
    )
}
