package woowacourse.payments.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.payments.MainActivity
import woowacourse.payments.component.PaymentToolbar
import woowacourse.payments.core.Event
import woowacourse.payments.core.getParcelableCompat
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardType
import woowacourse.payments.serialization.SerializationCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards = remember { mutableStateListOf<Card>() }
                var uiEvent by remember {
                    mutableStateOf<Event<CardScreenUiEvent>>(
                        Event(CardScreenUiEvent.Idle)
                    )
                }

                val activityResultLauncher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == RESULT_OK) {
                        result.data?.getParcelableCompat<SerializationCard>(EXTRA_CARD)?.let {
                            cards.add(it.toDomain())
                            uiEvent = Event(CardScreenUiEvent.CompleteAddCard)
                        }
                    }
                }

                Scaffold(
                    topBar = {
                        PaymentToolbar(
                            onAddClick = {
                                activityResultLauncher.launch(
                                    MainActivity.newIntent(this)
                                )
                            },
                            addButtonVisible = cards.size > 1
                        )
                    }
                ) { innerPadding ->
                    CardsScreen(
                        cards = cards,
                        uiEvent = uiEvent.peekContent(),
                        onClickCard = { cardType ->
                            if (cardType == CardType.EMPTY) {
                                activityResultLauncher.launch(
                                    MainActivity.newIntent(this)
                                )
                            }
                        },
                        Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            card: SerializationCard
        ): Intent = Intent(context, CardsActivity::class.java)
            .apply { putExtra(EXTRA_CARD, card) }

        private const val EXTRA_CARD = "extra_card"
    }
}
