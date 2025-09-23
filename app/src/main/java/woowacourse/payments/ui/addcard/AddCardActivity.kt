package woowacourse.payments.ui.addcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.bottomsheet.CardCompanyBottomSheet
import woowacourse.payments.ui.common.ExtraKeys
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val type: CardScreenType =
            intent.getParcelableExtraCompat<CardScreenType>(ExtraKeys.KEY_CARD_SCREEN_TYPE) ?: run {
                finish()
                return
            }

        setContent {
            AndroidpaymentsTheme {
                val stateHolder = remember { AddCardScreenUiStateHolder(type) }

                AddCardScreen(
                    stateHolder = stateHolder,
                    onSaveSuccess = { card: CardUiModel ->
                        Toast
                            .makeText(this, R.string.add_card_success_message, Toast.LENGTH_SHORT)
                            .show()
                        submitAddedCard(type, card)
                    },
                    onSaveFailure = {
                        Toast
                            .makeText(this, R.string.add_card_failure_message, Toast.LENGTH_SHORT)
                            .show()
                    },
                    onBackClick = { finish() },
                )

                CardCompanyBottomSheet(
                    AddCardScreenUiStateHolder.CARD_COMPANIES,
                    stateHolder::onCardCompanySelected,
                    { finish() },
                )
            }
        }
    }

    private fun submitAddedCard(
        type: CardScreenType,
        card: CardUiModel,
    ) {
        val result: Intent =
            Intent().apply {
                putExtra(ExtraKeys.KEY_CARD_SCREEN_TYPE, type)
                putExtra(ExtraKeys.KEY_ADDED_CARD, card)
            }
        setResult(RESULT_OK, result)
        finish()
    }

    companion object {
        fun intent(
            context: Context,
            mode: CardScreenType,
        ): Intent =
            Intent(context, AddCardActivity::class.java).apply {
                putExtra(ExtraKeys.KEY_CARD_SCREEN_TYPE, mode)
            }
    }
}
