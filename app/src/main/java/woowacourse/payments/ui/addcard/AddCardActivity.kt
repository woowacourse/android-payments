package woowacourse.payments.ui.addcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R
import woowacourse.payments.ui.ExtraKeys
import woowacourse.payments.ui.model.CardUiModel

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddCardContents(
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
