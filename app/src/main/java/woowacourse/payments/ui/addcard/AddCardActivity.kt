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

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddCardContents(
                onSaveSuccess = { cardNumber: String, expirationDate: String, cardholderName: String, passcode: String ->
                    submitAddedCard(cardNumber, expirationDate, cardholderName, passcode)
                    Toast
                        .makeText(this, R.string.add_card_success_message, Toast.LENGTH_SHORT)
                        .show()
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

    private fun submitAddedCard(
        cardNumber: String,
        expirationDate: String,
        cardholderName: String,
        passcode: String,
    ) {
        Intent()
            .apply {
                putExtra(ExtraKeys.CARD_NUMBER_KEY, cardNumber)
                putExtra(ExtraKeys.CARD_EXPIRATION_DATE_KEY, expirationDate)
                putExtra(ExtraKeys.CARDHOLDER_NAME_KEY, cardholderName)
                putExtra(ExtraKeys.CARD_PASSCODE_KEY, passcode)
            }.let { result: Intent -> setResult(RESULT_OK, result) }
        finish()
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, AddCardActivity::class.java)
    }
}
