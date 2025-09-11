package woowacourse.payments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.ui.features.cardlist.CardListScreen
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

class CardlistActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardUiModels = remember { mutableStateListOf<PaymentCardUiModel>() }

                val cardAddLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        if (activityResult.resultCode == Activity.RESULT_OK) {
                            val newCard =
                                activityResult.data?.getParcelableExtraCompat<PaymentCardUiModel>(
                                    AddcardActivity.EXTRA_PAYMENT_CARD_UI_MODEL,
                                )
                            newCard?.let {
                                cardUiModels.add(it)
                                showToast(this, R.string.card_list_card_added_alert)
                            }
                        }
                    }

                CardListScreen(
                    cardUiModels = cardUiModels,
                    onAddCard = {
                        val intent = Intent(this, AddcardActivity::class.java)
                        cardAddLauncher.launch(intent)
                    },
                )
            }
        }
    }

    private fun showToast(
        context: Context,
        messageId: Int,
    ) {
        Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
    }
}
