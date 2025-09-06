package woowacourse.payments.cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cards)
        setContent {
            AndroidpaymentsTheme {
                Scaffold { innerPadding ->
                    val cards = remember { mutableStateListOf<Card>() }

                    CardsScreen(
                        cards,
                        Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
