package woowacourse.payments.ui.addcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardCreationScreen(
                onBackClick = { finish() },
                onSaveClick = {
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_CARD, it)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                },
            )
        }
    }

    companion object {
        const val EXTRA_CARD = "card"
    }
}
