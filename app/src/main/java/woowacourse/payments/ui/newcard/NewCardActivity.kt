package woowacourse.payments.ui.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.newcard.components.NewCardTopBar

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        NewCardTopBar(
                            onBackClick = { finish() },
                            onSaveClick = { /* TODO: 저장 기능 추가 */ },
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    NewCardScreen(
                        contentPadding = innerPadding,
                        onSaved = { /* TODO: 저장 기능 추가 */ },
                    )
                }
            }
        }
    }
}
