package woowacourse.payments.ui.cardRegister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.ui.common.model.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val context = LocalContext.current
                CardRegisterScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card: Card ->
                        val intent =
                            Intent().putExtra(
                                "newCard",
                                card,
                            )
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                    isNotValidInput = {
                        Toast.makeText(context, "입력값을 확인해주세요", Toast.LENGTH_SHORT).show()
                    },
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CardRegisterActivity::class.java)
    }
}
