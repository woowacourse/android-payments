package woowacourse.payments.ui.cardwallet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardWalletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidpaymentsTheme {
                val cardList = rememberSaveable { mutableStateListOf<CardUiModel>() }

                CardWalletScreen(
                    cardList = cardList,
                    onCardAddOrUpdate = { newCard, index ->
                        if (index != null) {
                            cardList[index] = newCard
                        } else {
                            cardList.add(newCard)
                            Toast
                                .makeText(
                                    this,
                                    getString(R.string.add_card_confirm),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    },
                )
            }
        }
    }
}
