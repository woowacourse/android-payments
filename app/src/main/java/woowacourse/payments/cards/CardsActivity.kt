package woowacourse.payments.cards

import woowacourse.payments.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import woowacourse.payments.component.PaymentToolbar
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards = remember { mutableStateListOf<Card>() }

                Scaffold(
                    topBar = {
                        PaymentToolbar(
                            onAddClick = {},
                            addButtonVisible = cards.size > 1
                        )
                    }
                ) { innerPadding ->
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
