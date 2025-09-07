package woowacourse.payments.ui.screen.cardList

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.screen.addCard.AddCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardList = remember { mutableStateListOf<CardUiModel>() }

                val addCardLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val newCard =
                                result.data?.getParcelableExtraCompat<CardUiModel>("new_card")
                            newCard?.let { cardList.add(it) }
                        }
                    }

                CardListScreen(
                    cards = cardList,
                    navigateToAddCard = {
                        val intent = Intent(this@CardListActivity, AddCardActivity::class.java)
                        addCardLauncher.launch(intent)
                    },
                )
            }
        }
    }
}
