package woowacourse.payments.ui.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(onBackClick = {}, onSaveClick = {})
                    },
                ) { innerPadding ->
                    NewCardScreen(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NewCardTopBar(onBackClick = {}, onSaveClick = {})
            },
        ) { innerPadding ->
            NewCardScreen(innerPadding)
        }
    }
}
