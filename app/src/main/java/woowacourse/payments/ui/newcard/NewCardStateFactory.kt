package woowacourse.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.domain.CardCompany

@Composable
fun rememberNewCardState(): NewCardState =
    rememberSaveable(saver = NewCardStateSaver) {
        NewCardState()
    }

private val NewCardStateSaver =
    Saver<NewCardState, List<String?>>(
        save = {
            listOf(
                it.cardCompany?.name,
                it.cardNumber,
                it.cardExpirationDate,
                it.cardHolderName,
                it.cardPassword,
            )
        },
        restore = {
            NewCardState().apply {
                it[0]?.let { enumName ->
                    onCompanySelected(CardCompany.valueOf(enumName))
                }
                onCardNumberChange(it[1] ?: "")
                onCardExpirationDateChange(it[2] ?: "")
                onCardHolderNameChange(it[3] ?: "")
                onCardPasswordChange(it[4] ?: "")
            }
        },
    )
