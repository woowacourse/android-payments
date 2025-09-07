package woowacourse.payments.ui.view.new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.view.cards.CardsActivity
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                var card by remember { mutableStateOf(Card.EMPTY) }
                Scaffold(
                    topBar = {
                        NewCardTopBar(
                            onBackClick = { finish() },
                            onSaveClick = {
                                val intent =
                                    CardsActivity.newIntent(this, card.toSerializationCard())
                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NewCardScreen(
                        card = card,
                        onCardChange = { card = it },
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}
