package woowacourse.payments.ui.addcard

import android.content.Context
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.addcard.model.ModificationMode
import woowacourse.payments.ui.addcard.model.VendorModalUiModel
import woowacourse.payments.ui.addcard.model.VendorModalUiState
import woowacourse.payments.ui.allcards.AllCardsActivity.Companion.CARD_INFO_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.isComplete
import woowacourse.payments.ui.util.getParcelableCompat

class AddCardActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val initialCardInfo = intent.getParcelableCompat<CardInfoUiState>(CARD_INFO_KEY)
            val cardInfo =
                rememberSaveable {
                    initialCardInfo ?: CardInfoUiState()
                }
            val vendorModal =
                rememberSaveable {
                    VendorModalUiState(
                        VendorModalUiModel(
                            isVisible = initialCardInfo == null,
                        ),
                    )
                }

            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AddCardTopbar(
                            modificationMode =
                                if (initialCardInfo == null) {
                                    ModificationMode.ADD_CARD
                                } else {
                                    ModificationMode.MODIFY_CARD
                                },
                            isAddCardEnabled = cardInfo.isComplete(),
                            onAddCardSuccess = { saveCard(cardInfo) },
                            onBackClick = { finish() },
                        )
                    },
                ) { padding ->
                    AddCardScreen(
                        cardInfo = cardInfo,
                        vendorModal = vendorModal,
                        modifier =
                            Modifier
                                .padding(padding)
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth(),
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

    companion object {
        fun newIntent(
            context: Context,
            cardInfo: CardInfoUiState = CardInfoUiState(),
        ) = Intent(context, AddCardActivity::class.java).apply {
            putExtra(CARD_INFO_KEY, cardInfo)
        }
    }
}
