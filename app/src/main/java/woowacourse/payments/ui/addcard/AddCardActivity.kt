package woowacourse.payments.ui.addcard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.allcards.AllCardsActivity.Companion.CARD_INFO_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.isComplete

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cardInfo by rememberSaveable { mutableStateOf(CardInfoUiState()) }
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AddCardTopbar(
                            onBackClick = { finish() },
                            onCheckedClick = { saveCard(cardInfo) },
                        )
                    },
                ) { padding ->
                    AddCardScreen(
                        modifier =
                            Modifier
                                .padding(padding)
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth(),
                        cardInfo = cardInfo,
                    )
                }
            }
        }
    }

    private fun saveCard(cardInfo: CardInfoUiState) {
        if (cardInfo.isComplete()) {
            setResult(
                RESULT_OK,
                Intent().apply {
                    putExtra(CARD_INFO_KEY, cardInfo)
                },
            )
            finish()
        } else {
            Toast
                .makeText(this, getString(R.string.addcard_failed_to_add_card), Toast.LENGTH_SHORT)
                .show()
        }
    }
}
