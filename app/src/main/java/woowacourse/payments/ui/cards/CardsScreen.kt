package woowacourse.payments.ui.cards

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cards.components.Cards
import woowacourse.payments.ui.cards.components.CardsTopBar
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity.Companion.EXTRA_NEW_CARD

@Composable
fun CardsScreen(minimumCardCountForAddButton: Int = 0) {
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
                cardAddLauncher = cardAddLauncher,
                isAddable = cardList.size > minimumCardCountForAddButton,
            )
        },
    ) { innerPadding ->
        Cards(
            innerPadding = innerPadding,
            cardAddLauncher = cardAddLauncher,
            cardList = cardList,
            minimumCardCountForAddButton = minimumCardCountForAddButton,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    CardsScreen()
}
