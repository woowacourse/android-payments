package woowacourse.payments

import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.domain.model.CardVendor
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.uimodel.CardInfoUiModel
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.toUiModel

val cards =
    mutableStateListOf(
        CardInfoUiState(
            CardInfoUiModel(
                cardNumber = "1234123412341234",
                password = "1234",
                ownerName = "홍길동홍길동홍길동홍길동홍길동홍길동",
                expireDate = "1225",
                vendor = CardVendor.BCCard.toUiModel(),
            ),
        ),
        CardInfoUiState(
            CardInfoUiModel(
                cardNumber = "1234123412341234",
                password = "1234",
                ownerName = "홍길동",
                expireDate = "1225",
            ),
        ),
        CardInfoUiState(
            CardInfoUiModel(
                cardNumber = "1234123412341234",
                password = "1234",
                ownerName = "홍길동",
                expireDate = "1225",
            ),
        ),
    )

val singleAllCardUiState =
    AllCardsUiState(
        mutableListOf(
            cards.first(),
        ),
    )

val multipleAllCardUiState = AllCardsUiState(cards)
