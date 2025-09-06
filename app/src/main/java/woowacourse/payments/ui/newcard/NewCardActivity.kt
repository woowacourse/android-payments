package woowacourse.payments.ui.newcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewCardContents(
                onSaveSuccess = {
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
}
