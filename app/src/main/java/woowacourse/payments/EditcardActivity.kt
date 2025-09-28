package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import woowacourse.payments.data.PaymentFakeRepository
import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.ui.features.cartinput.CardInputScreen
import woowacourse.payments.ui.features.cartinput.CardUiStateHolder
import woowacourse.payments.ui.mapper.CardMapper.toUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel.Companion.EMPTY_DB_ID
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

class EditcardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val initialCardModel =
            intent.getParcelableExtraCompat<PaymentCardUiModel>(EXTRA_INITIAL_CARD_UI_MODEL)
                ?: run {
                    finish()
                    return
                }

        val cardUiState =
            PaymentFakeRepository.getCardUiStateById(initialCardModel.dbId)
                ?: run {
                    finish()
                    return
                }
        setContent {
            AndroidpaymentsTheme {
                val cardUiStateHolder =
                    rememberSaveable(saver = CardUiStateHolder.Saver) {
                        CardUiStateHolder(initialUiState = cardUiState)
                    }
                CardInputScreen(
                    dbId = initialCardModel.dbId,
                    cardUiStateHolder = cardUiStateHolder,
                    screenTitle = stringResource(R.string.edit_card_top_bar_title),
                    onNavigateBack = { finish() },
                    onNavigateSave = { id, card: PaymentCard ->
                        val intent =
                            Intent().apply {
                                putExtra(EXTRA_CARD_DB_ID, id)
                                putExtra(EXTRA_PAYMENT_CARD_UI_MODEL, card.toUiModel())
                            }
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARD_DB_ID = "EDIT_EXTRA_CARD_ID"
        private const val EXTRA_PAYMENT_CARD_UI_MODEL = "EDIT_EXTRA_PAYMENT_CARD"

        private const val EXTRA_INITIAL_CARD_UI_MODEL = "EDIT_EXTRA_INITIAL_CARD_UI_MODEL"

        fun newIntent(
            context: Context,
            cardToEdit: PaymentCardUiModel,
        ): Intent =
            Intent(context, EditcardActivity::class.java).apply {
                putExtra(EXTRA_INITIAL_CARD_UI_MODEL, cardToEdit)
            }

        fun parsePaymentCardUiModelByEditCard(activityResult: ActivityResult): PaymentCardUiModel? {
            if (activityResult.resultCode != RESULT_OK) {
                return null
            }

            val cardDBId = activityResult.data?.getIntExtra(EXTRA_CARD_DB_ID, EMPTY_DB_ID) ?: EMPTY_DB_ID
            val paymentCardUiModel =
                activityResult.data?.getParcelableExtraCompat<PaymentCardUiModel>(
                    EXTRA_PAYMENT_CARD_UI_MODEL,
                ) ?: return null
            return paymentCardUiModel.copy(dbId = cardDBId)
        }
    }
}
