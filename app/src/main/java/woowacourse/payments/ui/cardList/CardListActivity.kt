package woowacourse.payments.ui.cardList

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.R
import woowacourse.payments.ui.cardRegister.CardRegisterActivity
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.common.parcelable
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards =
                    rememberSaveable { mutableStateOf(emptyList<CardUiModel>()) }
                val cardAddLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                        when (activityResult.resultCode) {
                            CardRegisterActivity.NEW_CARD_SAVE_RESULT_OK -> {
                                val newCard: CardUiModel =
                                    activityResult.data?.parcelable(NEW_CARD_KEY)
                                        ?: return@rememberLauncherForActivityResult
                                cards.value = (cards.value.toMutableList() + newCard).toList()
                                Toast
                                    .makeText(
                                        this,
                                        getString(R.string.registration_card_complete_message),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                            }

                            CardRegisterActivity.EDIT_CARD_SAVE_RESULT_OK -> {
                                val newCard: CardUiModel =
                                    activityResult.data?.parcelable(EDITED_CARD_KEY)
                                        ?: return@rememberLauncherForActivityResult
                                cards.value =
                                    cards.value.map { card ->
                                        if (card.id == newCard.id) {
                                            newCard
                                        } else {
                                            card
                                        }
                                    }
                            }
                        }
                    }
                CardListScreen(
                    cards = cards.value,
                    onCardClick = { card: CardUiModel ->
                        cardAddLauncher.launch(CardRegisterActivity.newIntent(this, true, card))
                    },
                    onRegistrationClick = {
                        cardAddLauncher.launch(CardRegisterActivity.newIntent(this))
                    },
                )
            }
        }
    }

    companion object {
        const val NEW_CARD_KEY = "com.woowacourse.payments.ui.cardList.NEW_CARD_KEY"
        const val EDITED_CARD_KEY = "com.woowacourse.payments.ui.cardList.EDITED_CARD_KEY"
    }
}
