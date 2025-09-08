package woowacourse.payments.ui

import androidx.compose.runtime.saveable.mapSaver
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.ui.screen.cardAddition.CardAdditionUiState

val CardAdditionUiStateSaver = mapSaver(
    save = { cardAdditionUiState: CardAdditionUiState ->
        mapOf(
            "number" to cardAdditionUiState.cardNumber.value,
            "date" to cardAdditionUiState.expiredDate.value,
            "name" to cardAdditionUiState.ownerName,
            "password" to cardAdditionUiState.password.value,
        )
    },
    restore = { saver: Map<String, Any?> ->
        CardAdditionUiState(
            cardNumber = CardNumber(saver["number"] as String),
            expiredDate = ExpiredDate(saver["date"] as String),
            ownerName = saver["name"] as String,
            password = CardPassword(saver["password"] as String),
        )
    },
)