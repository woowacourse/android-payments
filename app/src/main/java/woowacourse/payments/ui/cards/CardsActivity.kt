package woowacourse.payments.ui.cards

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.cardcreate.CreateCardActivity
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.paymentCardUiModelSamples
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardsStateHolder = rememberSaveable(saver = CardsStateHolderSaver()) {
                    CardsStateHolder(CardsUiState.of(emptyList()))
                }
                val cardAddLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult()
                    ) { activityResult ->
                        if (activityResult.resultCode == RESULT_OK) {
                            val data = activityResult.data
                            val cardUiModel = data?.getParcelableExtra(
                                NEW_CARD_KEY,
                                PaymentCardUiModel::class.java
                            ) ?: return@rememberLauncherForActivityResult
                            cardsStateHolder.addCard(cardUiModel)
                        }
                    }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CardsTopBar(
                            cardsStateHolder.cardsUiState,
                            onAddClick = {
                                val intent = CreateCardActivity.instance(this)
                                cardAddLauncher.launch(intent)
                            })
                    }) { innerPadding ->
                    CardsScreen(
                        cardsStateHolder.cardsUiState,
                        {
                            val intent = CreateCardActivity.instance(this)
                            cardAddLauncher.launch(intent)
                        },
                        Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

    companion object {
        private const val NEW_CARD_KEY = "new_card_key"
        fun intent(cardUiModel: PaymentCardUiModel) = Intent().putExtra(NEW_CARD_KEY, cardUiModel)
    }
}