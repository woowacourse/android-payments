package woowacourse.payments

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import java.time.YearMonth

val CARD_FIXTURE: CardUiModel =
    Card(
        CardNumber("1234123412341234"),
        ExpirationDate(YearMonth.of(2099, 12)),
        CardholderName("디랙"),
        Passcode("1234"),
        CardCompany.KB_CARD,
    ).toUiModel()
