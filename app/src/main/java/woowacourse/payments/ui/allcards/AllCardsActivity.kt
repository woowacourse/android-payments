package woowacourse.payments.ui.allcards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.addcard.model.ModificationMode
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.util.getParcelableCompat
import woowacourse.payments.ui.util.getSerializableCompat
import woowacourse.payments.ui.util.showToast

class AllCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val allCards = rememberSaveable { AllCardsUiState(listOf()) }
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val modificationMode =
                            result.data?.getSerializableCompat<ModificationMode>(
                                CARD_MODIFICATION_MODE_KEY,
                            )
                        val cardInfo =
                            result.data?.getParcelableCompat<CardInfoUiState>(CARD_INFO_KEY)

                        val index =
                            result.data?.getSerializableCompat<Int>(CARD_MODIFICATION_INDEX_KEY)

                        if (modificationMode != null && cardInfo != null) {
                            when (modificationMode) {
                                ModificationMode.ADD_CARD -> {
                                    allCards.addCard(cardInfo)
                                    showToast(getString(R.string.allcards_card_added))
                                }

                                ModificationMode.MODIFY_CARD -> {
                                    allCards.modifyCard(
                                        cardInfo,
                                        index,
                                    )
                                    showToast(getString(R.string.allcards_card_modified))
                                }
                            }
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
                                launcher.launch(AddCardActivity.newIntent(this))
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
                        onCardClick = { cardInfo, index ->
                            launcher.launch(AddCardActivity.newIntent(this, cardInfo, index))
                        },
                    )
                }
            }
        }
    }

    companion object {
        const val CARD_INFO_KEY = "cardInfo"
        const val CARD_MODIFICATION_MODE_KEY = "cardAddMode"
        const val CARD_MODIFICATION_INDEX_KEY = "cardIndex"
    }
}
