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
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder = remember { AddCardScreenUiStateHolder() }

                AddCardScreen(
                    stateHolder = stateHolder,
                    onSaveSuccess = { card: CardUiModel ->
                        Toast
                            .makeText(this, R.string.add_card_success_message, Toast.LENGTH_SHORT)
                            .show()
                        submitAddedCard(card)
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

    private fun submitAddedCard(card: CardUiModel) {
        val result: Intent = Intent().putExtra(ExtraKeys.CARD_KEY, card)
        setResult(RESULT_OK, result)
        finish()
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, AddCardActivity::class.java)
    }
}
