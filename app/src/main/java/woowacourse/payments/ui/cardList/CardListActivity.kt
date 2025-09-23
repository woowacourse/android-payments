package woowacourse.payments.ui.cardList

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
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
                val cards = rememberSaveable { mutableStateListOf<CardUiModel>() }
                val cardAddLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                        if (activityResult.resultCode == RESULT_OK) {
                            val newCard: CardUiModel =
                                activityResult.data?.parcelable(NEW_CARD_KEY)
                                    ?: return@rememberLauncherForActivityResult
                            cards += newCard
                            Toast
                                .makeText(
                                    this,
                                    getString(R.string.registration_card_complete_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    }
                CardListScreen(
                    cards = cards,
                    onRegistrationClick = {
                        cardAddLauncher.launch(CardRegisterActivity.newIntent(this))
                    },
                )
            }
        }
    }

    companion object {
        const val NEW_CARD_KEY = "com.woowacourse.payments.ui.cardList.NEW_CARD_KEY"
    }
}
