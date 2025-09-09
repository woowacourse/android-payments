package woowacourse.payments.ui.screen.cardList

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
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.screen.addCard.AddCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                val context = LocalContext.current
                val cards = rememberSaveable { mutableStateListOf<CardUiModel>() }

                val addCardLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val newCard =
                                result.data?.getParcelableExtraCompat<CardUiModel>("new_card")
                            newCard?.let {
                                cards.add(it)
                                Toast.makeText(context, "새 카드가 추가되었습니다", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                CardListScreen(
                    cards = cards,
                    navigateToAddCard = {
                        val intent = Intent(this@CardListActivity, AddCardActivity::class.java)
                        addCardLauncher.launch(intent)
                    },
                )
            }
        }
    }
}
