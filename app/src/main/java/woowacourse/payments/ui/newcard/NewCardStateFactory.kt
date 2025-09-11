package woowacourse.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberNewCardState(): NewCardState =
    rememberSaveable(saver = NewCardStateSaver) {
        NewCardState()
    }

private val NewCardStateSaver =
    Saver<NewCardState, List<String>>(
        save = { listOf(it.cardNumber, it.cardExpirationDate, it.cardHolderName, it.cardPassword) },
        restore = {
            NewCardState().apply {
                onCardNumberChange(it[0])
                onCardExpirationDateChange(it[1])
                onCardHolderNameChange(it[2])
                onCardPasswordChange(it[3])
            }
        },
    )
