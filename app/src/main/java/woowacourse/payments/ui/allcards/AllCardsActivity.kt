package woowacourse.payments.ui.allcards

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AllCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cards = rememberSaveable { mutableStateListOf<CardInfoUiState>() }
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        result.data?.getCardInfo()?.let {
                            cards.add(it)
                        }
                    }
                }
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AllCardsTopbar(
                            cards,
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                            onPlusCardClick = {
                                launcher.launch(Intent(this, AddCardActivity::class.java))
                            }
                        )
                    }
                ) { padding ->
                    AllCardsScreen(
                        cards,
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxWidth(),
                        onPlusCardClick = {
                            launcher.launch(Intent(this, AddCardActivity::class.java))
                        }
                    )
                }
            }
        }
    }

    private fun Intent.getCardInfo() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelableExtra(CARD_INFO_KEY, CardInfoUiState::class.java)
        } else {
            getParcelableExtra(CARD_INFO_KEY)
        }

    companion object {
        const val CARD_INFO_KEY = "cardInfo"
    }
}