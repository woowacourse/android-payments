package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.data.BankRepository
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    private val newCardStateHolder = NewCardStateHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    banks = BankRepository.getBanks(),
                    newCardStateHolder = newCardStateHolder,
                    onBackPress = { finish() },
                    onSaved = { paymentCard ->
                        runCatching {
                            setResult(RESULT_OK, Intent().putExtra(EXTRA_NEW_CARD, paymentCard))
                            finish()
                        }.onFailure { e ->
                            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                        }
                    },
                )
            }
        }
    }

    companion object {
        const val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"

        fun newIntent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}
