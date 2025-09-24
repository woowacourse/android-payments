package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.ui.common.ExtraKeys
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.submitcard.CardScreenType
import woowacourse.payments.ui.submitcard.SubmitCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards = remember { mutableStateListOf<CardUiModel>() }

                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                        if (result.resultCode == RESULT_OK) {
                            val data: Intent =
                                result.data ?: return@rememberLauncherForActivityResult
                            val type: CardScreenType =
                                data.getParcelableExtraCompat<CardScreenType>(
                                    ExtraKeys.KEY_SUBMIT_CARD_SCREEN_TYPE,
                                ) ?: return@rememberLauncherForActivityResult
                            val card: CardUiModel =
                                data
                                    .getParcelableExtraCompat<CardUiModel>(ExtraKeys.KEY_SUBMITTED_CARD)
                                    ?: return@rememberLauncherForActivityResult
                            when (type) {
                                is CardScreenType.AddCard -> cards.add(card)
                                is CardScreenType.EditCard -> cards[type.index] = card
                            }
                        }
                    }

                CardListScreen(
                    cards = cards,
                    onNavigateToAddCard = {
                        launcher.launch(
                            SubmitCardActivity.intent(
                                this,
                                CardScreenType.AddCard,
                            ),
                        )
                    },
                    onNavigateToEditCard = { index: Int, card: CardUiModel ->
                        launcher.launch(
                            SubmitCardActivity.intent(
                                this,
                                CardScreenType.EditCard(index, card),
                            ),
                        )
                    },
                )
            }
        }
    }
}
