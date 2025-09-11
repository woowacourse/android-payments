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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.features.cardlist.CardListScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

class CardlistActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                var cardList by remember { mutableStateOf<List<PaymentCard>>(emptyList()) }

                val cardAddLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        if (activityResult.resultCode == Activity.RESULT_OK) {
                            val newCard =
                                activityResult.data?.getParcelableExtraCompat<PaymentCard>(
                                    AddcardActivity.EXTRA_PAYMENT_CARD,
                                )
                            newCard?.let {
                                cardList = cardList + it
                                showToast(this, R.string.card_list_card_added_alert)
                            }
                        }
                    }

                CardListScreen(
                    paymentCardList = cardList,
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
