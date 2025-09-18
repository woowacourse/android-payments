package woowacourse.payments.ui.addcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.addcard.model.VendorModalUiState
import woowacourse.payments.ui.allcards.AllCardsActivity.Companion.CARD_INFO_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.isComplete

class AddCardActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cardInfo = rememberSaveable { CardInfoUiState() }
            val vendorModal = rememberSaveable { VendorModalUiState() }

            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AddCardTopbar(
                            isAddCardEnabled = cardInfo.isComplete(),
                            onAddCardSucceeded = { saveCard(cardInfo) },
                            onBackClick = { finish() },
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
                        vendorModal = vendorModal,
                        onCardClick = { vendorModal.showModal() },
                    )
                }
            }
        }
    }

    private fun saveCard(cardInfo: CardInfoUiState) {
        setResult(
            RESULT_OK,
            Intent().apply {
                putExtra(CARD_INFO_KEY, cardInfo)
            },
        )
        finish()
    }
}
