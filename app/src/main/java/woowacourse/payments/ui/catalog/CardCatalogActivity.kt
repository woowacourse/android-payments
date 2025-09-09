package woowacourse.payments.ui.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.catalog.screen.CardCatalogScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardCatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardCatalogScreen()
            }
        }
    }
}