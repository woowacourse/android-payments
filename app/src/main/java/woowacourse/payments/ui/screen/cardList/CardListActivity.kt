package woowacourse.payments.ui.screen.cardList

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.addCard.AddCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                val stateHolder =
                    rememberSaveable(saver = CardListStateHolder.saver) { CardListStateHolder() }

                val addCardLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val card =
                                result.data?.getParcelableExtraCompat<CardUiModel>(NEW_CARD_KEY)
                            card?.let {
                                val isUpdate = stateHolder.upsertCard(it)
                                val messageRes =
                                    if (isUpdate) {
                                        R.string.card_list_update_card_toast
                                    } else {
                                        R.string.card_list_add_card_toast
                                    }
                                Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                CardListScreen(
                    stateHolder = stateHolder,
                    navigateToAddCard = { cardToEdit ->
                        val intent = Intent(this@CardListActivity, AddCardActivity::class.java)
                        cardToEdit?.let { intent.putExtra(EDIT_CARD_KEY, it) }
                        addCardLauncher.launch(intent)
                    },
                )
            }
        }
    }

    companion object {
        const val NEW_CARD_KEY = "NEW_CARD"
        const val EDIT_CARD_KEY = "EDIT_CARD"
    }
}
