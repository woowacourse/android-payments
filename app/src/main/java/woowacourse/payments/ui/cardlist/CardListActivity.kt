package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.remember
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.common.ExtraKeys
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.submitcard.SubmitCardActivity
import woowacourse.payments.ui.submitcard.SubmitCardMode
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder = remember { CardListStateHolder() }

                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                        if (result.resultCode == RESULT_OK) {
                            val data: Intent =
                                result.data ?: return@rememberLauncherForActivityResult
                            val card: Card =
                                data
                                    .getParcelableExtraCompat<CardUiModel>(ExtraKeys.KEY_SUBMITTED_CARD)
                                    ?.toCardOrNull()
                                    ?: return@rememberLauncherForActivityResult
                            stateHolder.updateCards(card)
                        }
                    }

                CardListScreen(
                    cards = stateHolder.cards.map(Card::toUiModel),
                    onNavigateToAddCard = {
                        launcher.launch(
                            SubmitCardActivity.intent(
                                this,
                                SubmitCardMode.Add,
                            ),
                        )
                    },
                    onNavigateToEditCard = { index: Int, card: CardUiModel ->
                        launcher.launch(
                            SubmitCardActivity.intent(
                                this,
                                SubmitCardMode.Edit(index, card),
                            ),
                        )
                    },
                )
            }
        }
    }
}
