package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.ExpirationDate
import woowacourse.payments.domain.model.Password
import woowacourse.payments.domain.model.UserName
import woowacourse.payments.ui.model.CardUiModel

object CardMapper {
    fun CardUiModel.toDomain(): Card =
        Card(
            type = cardCompany.type,
            cardNumber = CardNumber.create(cardNumberRaw),
            expirationDate = ExpirationDate.createFromRaw(expirationDateRaw),
            userName = UserName.create(userName.orEmpty()),
            password = Password.create(password),
        )
}
