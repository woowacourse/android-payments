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
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.getParcelableList
import woowacourse.payments.ui.screen.addCard.AddCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    private val cardList = mutableStateListOf<CardUiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val restoredList: List<CardUiModel> =
            savedInstanceState?.getParcelableList<CardUiModel>("cards") ?: emptyList()
        cardList.addAll(restoredList)

        setContent {
            AndroidpaymentsTheme {
                val context = LocalContext.current

                val addCardLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val newCard =
                                result.data?.getParcelableExtraCompat<CardUiModel>("new_card")
                            newCard?.let {
                                cardList.add(it)
                                Toast.makeText(context, "새 카드가 추가되었습니다", Toast.LENGTH_SHORT).show()
                            }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("cards", ArrayList(cardList))
    }
}
