package woowacourse.payments.ui.cards

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.components.AddCardImage
import woowacourse.payments.ui.cards.components.CardItem
import woowacourse.payments.ui.cards.components.CardsTopBar
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.NewCardActivity.Companion.EXTRA_NEW_CARD

@Composable
fun CardsScreen(minimumCardCountForAddButton: Int = 0) {
    val context = LocalContext.current

    val cardList =
        rememberSaveable { mutableStateListOf<PaymentCardUiModel>() }

    val cardAddLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard = activityResult.data?.getParcelableExtra<PaymentCardUiModel>(EXTRA_NEW_CARD)
                newCard?.let { cardList.add(it) }
            }
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CardsTopBar(
                cardList = cardList,
                minimumCardCountForAddButton = minimumCardCountForAddButton,
                cardAddLauncher = cardAddLauncher,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(top = 12.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            cardList.forEach { card ->
                CardItem(
                    paymentCard = card,
                )
            }

            if (cardList.size <= minimumCardCountForAddButton) {
                if (cardList.isEmpty()) {
                    Text(
                        text = stringResource(R.string.cards_no_card),
                    )
                }
                AddCardImage {
                    val intent = NewCardActivity.newIntent(context)
                    cardAddLauncher.launch(intent)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    CardsScreen()
}
