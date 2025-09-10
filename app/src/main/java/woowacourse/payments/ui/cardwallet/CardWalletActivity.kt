package woowacourse.payments.ui.cardwallet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardWalletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidpaymentsTheme {
                val context = LocalContext.current
                val cardList = remember { mutableStateListOf<CardUiModel>() }

                CardWalletScreen(
                    cardList = cardList,
                    onCardAddResult = { newCard ->
                        cardList.add(newCard)
                        Toast.makeText(
                            context,
                            context.getString(R.string.add_card_confirm),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
