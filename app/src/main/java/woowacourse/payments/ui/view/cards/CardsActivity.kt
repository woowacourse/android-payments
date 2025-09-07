package woowacourse.payments.ui.view.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.view.new.NewCardActivity

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsRoute(
                    onAddCardClick = { launcher ->
                        launcher.launch(NewCardActivity.newIntent(this))
                    }
                )
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            card: SerializationCard
        ): Intent = Intent(context, CardsActivity::class.java)
            .apply { putExtra(EXTRA_CARD, card) }

        const val EXTRA_CARD = "extra_card"
    }
}
