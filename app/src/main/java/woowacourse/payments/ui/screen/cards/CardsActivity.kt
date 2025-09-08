package woowacourse.payments.ui.screen.cards

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.R
import woowacourse.payments.ui.common.extension.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.CardAdditionActivity
import woowacourse.payments.ui.screen.cards.component.CardsScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cards = rememberSaveable { mutableStateListOf<CardUiModel>() }
            val context = LocalContext.current
            val launcher =
                rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                ) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val card = result.data?.getParcelableCompat<CardUiModel>(EXTRA_CARD)
                        card?.let { card -> cards.add(card) }
                        Toast
                            .makeText(
                                context,
                                R.string.cards_card_addition_success,
                                Toast.LENGTH_SHORT,
                            ).show()
                    }
                }
            AndroidpaymentsTheme {
                CardsScreen(
                    onAddClick = {
                        launcher.launch(CardAdditionActivity.newIntent(context))
                    },
                    cards = cards,
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARD = "EXTRA_CARD"

        fun newIntent(
            context: Context,
            card: CardUiModel,
        ): Intent =
            Intent(context, CardsActivity::class.java).apply {
                putExtra(EXTRA_CARD, card)
            }
    }
}
