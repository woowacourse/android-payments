package woowacourse.payments

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    activityResult.data?.getParcelableExtra(
                                        AddcardActivity.EXTRA_PAYMENT_CARD,
                                        PaymentCard::class.java,
                                    )
                                } else {
                                    @Suppress("DEPRECATION")
                                    activityResult.data?.getParcelableExtra(AddcardActivity.EXTRA_PAYMENT_CARD)
                                }
                            newCard?.let {
                                cardList = cardList + it
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
}
