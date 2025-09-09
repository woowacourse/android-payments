package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R
import woowacourse.payments.ui.ExtraKeys

class NewCardActivity :
    ComponentActivity(),
    NewCardAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewCardContents(
                onSaveSuccess = { cardNumber: String, expirationDate: String, cardholderName: String, passcode: String ->
                    submitNewCard(cardNumber, expirationDate, cardholderName, passcode)
                    Toast
                        .makeText(this, R.string.new_card_success_message, Toast.LENGTH_SHORT)
                        .show()
                },
                onSaveFailure = {
                    Toast
                        .makeText(this, R.string.new_card_failure_message, Toast.LENGTH_SHORT)
                        .show()
                },
                onBackClick = { finish() },
            )
        }
    }

    override fun submitNewCard(
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
        fun intent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}

interface NewCardAction {
    fun submitNewCard(
        cardNumber: String,
        expirationDate: String,
        cardholderName: String,
        passcode: String,
    )
}
