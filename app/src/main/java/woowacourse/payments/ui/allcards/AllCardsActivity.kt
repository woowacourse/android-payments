package woowacourse.payments.ui.allcards

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState

class AllCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val allCards = rememberSaveable { AllCardsUiState(listOf()) }
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        result.data?.getCardInfo()?.let {
                            allCards.addCard(it)
                            Toast
                                .makeText(
                                    this,
                                    getString(R.string.allcards_card_added),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    }
                }
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AllCardsTopbar(
                            allCards = allCards,
                            modifier =
                                Modifier
                                    .padding(horizontal = 20.dp),
                            onPlusCardClick = {
                                launcher.launch(Intent(this, AddCardActivity::class.java))
                            },
                        )
                    },
                ) { padding ->
                    AllCardsScreen(
                        allCards = allCards,
                        modifier =
                            Modifier
                                .padding(padding)
                                .fillMaxWidth(),
                        onPlusCardClick = {
                            launcher.launch(Intent(this, AddCardActivity::class.java))
                        },
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
