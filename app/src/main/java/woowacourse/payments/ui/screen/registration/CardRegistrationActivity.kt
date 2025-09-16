package woowacourse.payments.ui.screen.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegistrationScreen(
                    onBackClick = ::finish,
                    onRegistrationComplete = { newCard ->
                        setResult(RESULT_OK, Intent().putExtra(EXTRA_NEW_CARD, newCard))
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"

        fun newIntent(context: Context): Intent = Intent(context, CardRegistrationActivity::class.java)
    }
}
