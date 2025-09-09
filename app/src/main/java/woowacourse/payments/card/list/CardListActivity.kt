package woowacourse.payments.card.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.card.register.CardRegisterActivity
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    private val _cards = mutableStateOf(emptyList<CardUiModel>())
    private val cards by _cards

    private val cardRegisterLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val newCardUiModel =
                    result.data?.getParcelableExtra("new_card_ui_model") as? CardUiModel

                if (newCardUiModel != null) {
                    _cards.value += newCardUiModel
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidpaymentsTheme {
                val onAddNewCardClick: () -> Unit = {
                    val intent = Intent(this, CardRegisterActivity::class.java)
                    cardRegisterLauncher.launch(intent)
                }

                CardListScreen(
                    cards = cards,
                    onAddNewCardClick = onAddNewCardClick,
                )
            }
        }
    }
}
