package woowacourse.payments.fixture

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password

val CARD_CREAM =
    Card(
        cardNumber = CardNumber("1234567812345678"),
        expiredDate = ExpiredDate.of(4, 26)!!,
        ownerName = OwnerName("크림"),
        password = Password("1234"),
        cardCompany = CardCompany.HYUNDAI,
    )

val CARD_YAGUBOGU =
    Card(
        cardNumber = CardNumber("5678567856785678"),
        expiredDate = ExpiredDate.of(1, 30)!!,
        ownerName = OwnerName("야구보구"),
        password = Password("3333"),
        cardCompany = CardCompany.HANA,
    )

val CARD_UNNAMED =
    Card(
        cardNumber = CardNumber("2424353546464646"),
        expiredDate = ExpiredDate.of(5, 27)!!,
        ownerName = OwnerName(""),
        password = Password("0000"),
        cardCompany = CardCompany.KB,
    )
