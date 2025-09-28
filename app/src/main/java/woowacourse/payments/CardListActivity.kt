package woowacourse.payments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.AddCardActivity.Companion.parsePaymentCardUiModelByAddCard
import woowacourse.payments.EditcardActivity.Companion.parsePaymentCardUiModelByEditCard
import woowacourse.payments.ui.features.cardlist.CardListScreen
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardUiModels = rememberSaveable { mutableStateListOf<PaymentCardUiModel>() }

                val cardAddLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        val newCard =
                            parsePaymentCardUiModelByAddCard(activityResult)
                                ?: return@rememberLauncherForActivityResult
                        newCard.let {
                            cardUiModels.add(newCard)
                            showToast(this, R.string.card_list_card_added_alert)
                        }
                    }

                val cardEditLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        val editedCard =
                            parsePaymentCardUiModelByEditCard(activityResult)
                                ?: return@rememberLauncherForActivityResult
                        val index = cardUiModels.indexOfFirst { it.dbId == editedCard.dbId }
                        if (index != -1) {
                            cardUiModels[index] = editedCard
                            showToast(this, R.string.card_list_card_edited_alert)
                        }
                    }

                CardListScreen(
                    cardUiModels = cardUiModels,
                    onAddCard = {
                        val intent = AddCardActivity.newIntent(this)
                        cardAddLauncher.launch(intent)
                    },
                    onEditCard = { cardUiModel ->
                        val intent = EditcardActivity.newIntent(this, cardUiModel)
                        cardEditLauncher.launch(intent)
                    },
                )
            }
        }
    }

    private fun showToast(
        context: Context,
        messageId: Int,
    ) {
        Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
    }
}
