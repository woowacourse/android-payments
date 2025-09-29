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
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.addcard.model.ModificationMode
import woowacourse.payments.ui.addcard.model.VendorModalUiModel
import woowacourse.payments.ui.addcard.model.VendorModalUiState
import woowacourse.payments.ui.allcards.AllCardsActivity.Companion.CARD_MODIFICATION_MODE_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.isComplete
import woowacourse.payments.ui.util.getParcelableCompat

class AddCardActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val modificationMode =
                intent.getParcelableCompat<ModificationMode>(CARD_MODIFICATION_MODE_KEY)
                    ?: return@setContent
            val cardInfo =
                rememberSaveable {
                    modificationMode.cardInfo.copy()
                }
            val vendorModal =
                rememberSaveable {
                    VendorModalUiState(
                        VendorModalUiModel(
                            isVisible = modificationMode is ModificationMode.Add,
                        ),
                    )
                }

            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AddCardTopbar(
                            modificationMode = modificationMode,
                            isAddCardEnabled = cardInfo.isComplete(),
                            isModificationEnabled =
                                ModificationMode.isModificationEnabled(
                                    previous = modificationMode.cardInfo,
                                    current = cardInfo,
                                ),
                            onAddCardSuccess = {
                                saveCard(
                                    ModificationMode.Add(cardInfo),
                                )
                            },
                            onModifyCardSuccess = {
                                if (modificationMode !is ModificationMode.Modify) return@AddCardTopbar
                                saveCard(
                                    ModificationMode.Modify(
                                        cardInfo,
                                        modificationMode.id,
                                    ),
                                )
                            },
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

    private fun saveCard(modificationMode: ModificationMode) {
        setResult(
            RESULT_OK,
            Intent().apply {
                putExtra(CARD_MODIFICATION_MODE_KEY, modificationMode)
            },
        )
        finish()
    }

    companion object {
        fun newIntent(
            context: Context,
            modificationMode: ModificationMode,
        ) = Intent(context, AddCardActivity::class.java).apply {
            putExtra(CARD_MODIFICATION_MODE_KEY, modificationMode)
        }
    }
}
