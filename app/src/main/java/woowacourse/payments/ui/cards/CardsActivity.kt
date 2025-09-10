package woowacourse.payments.ui.cards

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.R
import woowacourse.payments.ui.cardcreate.CreateCardActivity
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.utils.ext.parcelable

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardsStateHolder = rememberSaveable(saver = CardsStateHolderSaver()) {
                    CardsStateHolder(CardsUiState.of(emptyList()))
                }
                val cardAddLauncher = cardAddLauncher(cardsStateHolder, LocalContext.current)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CardsTopBar(
                            cardsUiState = cardsStateHolder.cardsUiState,
                            onAddClick = {
                                val intent = CreateCardActivity.instance(this)
                                cardAddLauncher.launch(intent)
                            })
                    }) { innerPadding ->
                    CardsScreen(
                        cardsUiState = cardsStateHolder.cardsUiState,
                        onAddClick = {
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

    @Composable
    fun cardAddLauncher(cardsStateHolder: CardsStateHolder, context: Context) =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val intent = activityResult.data
                val cardUiModel = intent?.parcelable<PaymentCardUiModel>(
                    NEW_CARD_KEY,
                ) ?: return@rememberLauncherForActivityResult
                cardsStateHolder.addCard(cardUiModel)
                Toast.makeText(
                    context,
                    getString(R.string.created_card_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    companion object {
        private const val NEW_CARD_KEY = "new_card_key"
        fun intent(cardUiModel: PaymentCardUiModel) = Intent().putExtra(NEW_CARD_KEY, cardUiModel)
    }
}