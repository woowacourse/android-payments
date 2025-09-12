package woowacourse.payments.ui.screen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.AddCardActivity
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun PaymentScreenContainer() {
    var cards by remember { mutableStateOf(emptyList<CardUiModel>()) }
    val context = LocalContext.current

    val cardAddLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard = AddCardActivity.parseResult(activityResult.data)
                if (newCard != null) {
                    cards = cards + newCard
                }
            }
        }

    LaunchedEffect(cards.size) {
        if (cards.isNotEmpty()) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.payment_toast_card_added),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    PaymentScreen(
        cards = cards,
        onAddCardClick = {
            cardAddLauncher.launch(AddCardActivity.createIntent(context))
        },
    )
}
