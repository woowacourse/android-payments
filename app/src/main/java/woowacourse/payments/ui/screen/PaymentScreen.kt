package woowacourse.payments.ui.screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.AddCardActivity
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun PaymentScreen() {
    val state = rememberPaymentStateHolder()
    val context = LocalContext.current

    val cardAddLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                AddCardActivity.parseResult(result.data)?.let { card: CardUiModel ->
                    state.onCardAdded(card)
                }
            }
        }

    LaunchedEffect(state.cards.size) {
        if (state.cards.isNotEmpty()) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.payment_toast_card_added),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    PaymentContent(
        cards = state.cards,
        showTopAdd = state.showTopAdd,
        canAddMore = state.canAddMore,
        onAddCardClick = { cardAddLauncher.launch(AddCardActivity.newIntent(context)) },
    )
}
