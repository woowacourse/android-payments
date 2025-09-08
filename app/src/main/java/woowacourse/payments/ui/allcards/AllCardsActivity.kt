package woowacourse.payments.ui.allcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AllCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AllCardsTopbar(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        )
                    }
                ) { padding ->
                    AllCardsScreen(modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth())
                }
            }
        }
    }
}