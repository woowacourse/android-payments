package woowacourse.payments.ui.cards

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.NewCardActivity.Companion.EXTRA_NEW_CARD
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.extensions.getSerializableCompat

class CardsActivity : ComponentActivity() {
    private val cardsStateHolder = CardsStateHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cardAddLauncher =
                rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                ) { activityResult ->
                    if (activityResult.resultCode == RESULT_OK) {
                        val newCard =
                            activityResult.data?.getSerializableCompat<PaymentCardUiModel>(
                                EXTRA_NEW_CARD,
                            )
                        newCard?.let { cardsStateHolder.addCard(it) }
                        Toast.makeText(this, "카드가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

            AndroidpaymentsTheme {
                CardsScreen(
                    cardsStateHolder = cardsStateHolder,
                    onAddClick = {
                        cardAddLauncher.launch(NewCardActivity.newIntent(this))
                    },
                )
            }
        }
    }
}
