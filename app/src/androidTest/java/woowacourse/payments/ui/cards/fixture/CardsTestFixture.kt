package woowacourse.payments.ui.cards.fixture

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpirationDate

val CARD_ONE =
    Card(
        id = 1L,
        cardholderName = "CN",
        cardNumber = "1111222233334444",
        cardPassword = "1234",
        cardCompany = CardCompany.KAKAO,
        cardExpirationDate = CardExpirationDate.from(CardExpirationDate.toCardExpirationDateStatus("1199")),
    )
val CARD_TWO =
    Card(
        id = 2L,
        cardholderName = "CHAEN",
        cardNumber = "1111222233336666",
        cardPassword = "1234",
        cardCompany = CardCompany.HYUNDAE,
        cardExpirationDate = CardExpirationDate.from(CardExpirationDate.toCardExpirationDateStatus("0199")),
    )
