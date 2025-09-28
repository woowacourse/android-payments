package woowacourse.payments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import woowacourse.payments.ui.CardListStateHolder
import woowacourse.payments.ui.CardListUiEvent
import woowacourse.payments.ui.features.cardlist.CardListScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val scope = rememberCoroutineScope()
                val context = LocalContext.current

                val stateHolder =
                    rememberSaveable(saver = CardListStateHolder.Saver) {
                        CardListStateHolder()
                    }

                LaunchedEffect(Unit) {
                    stateHolder.uiEventFlow.collect { event ->
                        when (event) {
                            is CardListUiEvent.ShowToast -> {
                                Toast.makeText(context, event.messageId, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                val cardAddLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        scope.launch {
                            stateHolder.onAddCardResult(activityResult)
                        }
                    }

                val cardEditLauncher =
                    rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        scope.launch {
                            stateHolder.onEditCardResult(activityResult)
                        }
                    }

                CardListScreen(
                    cardUiModels = stateHolder.cardUiModels,
                    onAddCard = {
                        val intent = AddCardActivity.newIntent(this)
                        cardAddLauncher.launch(intent)
                    },
                    onEditCard = { cardUiModel ->
                        val intent = EditcardActivity.newIntent(this, cardUiModel)
                        cardEditLauncher.launch(intent)
                    },
                )
            }
        }
    }
}
