package woowacourse.payments.ui.cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.cardcreate.CreateCardActivity
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.model.paymentCardUiModelSamples
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardsStateHolder = rememberSaveable(saver = CardsStateHolderSaver()) {
                    CardsStateHolder(CardsUiState.of(paymentCardUiModelSamples))
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CardsTopBar(
                            onAddClick = ::navigateCreateCardActivity,
                        )
                    }) { innerPadding ->
                    CardsScreen(
                        cardsStateHolder,
                        ::navigateCreateCardActivity,
                        Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

    fun navigateCreateCardActivity() {
        val intent = CreateCardActivity.instance(this)
        startActivity(intent)
    }
}