package woowacourse.payments.ui.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.AddCardActivity
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun PaymentScreenWithLauncher() {
    var cards by remember { mutableStateOf(emptyList<CardUiModel>()) }
    val context = LocalContext.current

    val cardAddLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard = activityResult.data?.getParcelableExtra<CardUiModel>("card")
                if (newCard != null) {
                    cards = cards + newCard
                }
            }
        }

    PaymentScreen(
        cards = cards,
        onAddCardClick = {
            val intent = Intent(context, AddCardActivity::class.java)
            cardAddLauncher.launch(intent)
        },
    )
}
