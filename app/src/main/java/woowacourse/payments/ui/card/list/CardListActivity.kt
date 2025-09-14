package woowacourse.payments.ui.card.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.card.register.CardRegisterActivity
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    private var cards by mutableStateOf<List<CardUiModel>>(emptyList())

    private val cardRegisterLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val newCard =
                    result.data?.getParcelableExtra<CardUiModel>(
                        CardRegisterActivity.EXTRA_NEW_CARD,
                    )
                newCard?.let { cards = cards + it }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidpaymentsTheme {
                CardListScreen(
                    cards = cards,
                    onAddNewCardClick = {
                        cardRegisterLauncher.launch(CardRegisterActivity.newIntent(this))
                    },
                )
            }
        }
    }
}
