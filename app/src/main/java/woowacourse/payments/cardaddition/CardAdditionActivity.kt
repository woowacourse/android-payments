package woowacourse.payments.cardaddition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import woowacourse.payments.cardaddition.component.CardAdditionScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder: CardAdditionStateHolder =
                    rememberSaveable(saver = CardAdditionStateHolder.Saver) { CardAdditionStateHolder() }

                CardAdditionScreen(
                    state = stateHolder.uiState,
                    onCardNumberChange = stateHolder::updateCardNumber,
                    onExpiredDateChange = stateHolder::updateExpiredDate,
                    onHolderChange = stateHolder::updateHolder,
                    onPasswordChange = stateHolder::updatePassword,
                    onSelectBank = stateHolder::updateBankType,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
