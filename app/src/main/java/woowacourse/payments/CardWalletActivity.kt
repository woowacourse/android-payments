package woowacourse.payments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
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
                        Toast.makeText(context, "카드가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
