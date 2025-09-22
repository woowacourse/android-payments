package woowacourse.payments.ui.cards

import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
class CardsStateHolder : Parcelable {
    val cardsState = mutableStateListOf<Card>()

    val isNewCardVisible by derivedStateOf { cardsState.size <= 1 }

    val isCardRegisterMessageVisible by derivedStateOf { cardsState.size == 0 }

    val isRegisteredCardsVisible by derivedStateOf { cardsState.size >= 1 }

    val isRegisterCardButtonVisible by derivedStateOf { cardsState.size > 1 }
}
