package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(
                            onBackClick = { finish() },
                            onSaveClick = { },
                        )
                    },
                ) { innerPadding ->
                    NewCardScreen(innerPadding)
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}
