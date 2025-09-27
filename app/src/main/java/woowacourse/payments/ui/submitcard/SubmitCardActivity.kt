package woowacourse.payments.ui.submitcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import woowacourse.payments.ui.common.ExtraKeys
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.submitcard.bottomsheet.CardCompanyBottomSheet
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class SubmitCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mode: SubmitCardMode =
            intent.getParcelableExtraCompat<SubmitCardMode>(ExtraKeys.KEY_SUBMIT_CARD_SCREEN_MODE)
                ?: run {
                    finish()
                    return
                }

        setContent {
            AndroidpaymentsTheme {
                val stateHolder =
                    remember {
                        when (mode) {
                            is SubmitCardMode.Add -> SubmitCardStateHolder.AddCardStateHolder()
                            is SubmitCardMode.Edit ->
                                SubmitCardStateHolder.EditCardStateHolder(
                                    mode.card,
                                )
                        }
                    }

                SubmitCardScreen(
                    stateHolder = stateHolder,
                    onSubmitClick = {
                        stateHolder.checkSubmission {
                            val result: Intent =
                                Intent().apply {
                                    putExtra(ExtraKeys.KEY_SUBMIT_CARD_SCREEN_MODE, mode)
                                    putExtra(ExtraKeys.KEY_SUBMITTED_CARD, stateHolder.card)
                                }
                            setResult(RESULT_OK, result)
                            finish()
                        }
                    },
                    onBackClick = { finish() },
                )

                if (stateHolder.showCardCompanies) {
                    CardCompanyBottomSheet(
                        SubmitCardStateHolder.CARD_COMPANIES,
                        stateHolder::onCardCompanySelected,
                        { finish() },
                    )
                }
            }
        }
    }

    companion object {
        fun intent(
            context: Context,
            mode: SubmitCardMode,
        ): Intent =
            Intent(context, SubmitCardActivity::class.java).apply {
                putExtra(ExtraKeys.KEY_SUBMIT_CARD_SCREEN_MODE, mode)
            }
    }
}
