package woowacourse.payments.ui.addcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.allcards.CARD_INFO_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

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
                            onCheckedClick = {
                                if (cardInfo.isComplete()) {
                                    setResult(RESULT_OK, Intent().apply {
                                        putExtra(CARD_INFO_KEY, cardInfo)
                                    })
                                    finish()
                                }
                            }
                        )
                    }
                ) { padding ->
                    AddCardScreen(padding, cardInfo)
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, AddCardActivity::class.java)
    }
}