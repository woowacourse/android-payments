package woowacourse.payments.ui.features.addcard

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_CARD_NUMBER
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_EXPIRE_DATE
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_PASSWORD
import java.time.YearMonth

object CardMapper {
    private fun checkValidCardNumber(cardNumber: String): Boolean =
        cardNumber.length == MAX_LENGTH_CARD_NUMBER && cardNumber.all(Char::isDigit)

    private fun checkValidPassword(password: String): Boolean = password.length == MAX_LENGTH_PASSWORD && password.all(Char::isDigit)

    private fun get21cYearMonth(
        yy: Int,
        mm: Int,
    ): YearMonth = YearMonth.of(2000 + yy, mm)

    fun getExpireDateStatus(expireDate: String): ExpireDateStatus {
        if (expireDate.length != MAX_LENGTH_EXPIRE_DATE) return ExpireDateStatus.Typing
        val mm = expireDate.take(2).toIntOrNull() ?: return ExpireDateStatus.InvalidFormat
        val yy = expireDate.drop(2).toIntOrNull() ?: return ExpireDateStatus.InvalidFormat
        if (mm !in 1..12) return ExpireDateStatus.InvalidMonth
        val typedYearMonth = get21cYearMonth(yy, mm)
        return if (typedYearMonth >= YearMonth.now()) {
            ExpireDateStatus.Valid(typedYearMonth)
        } else {
            ExpireDateStatus.Expired
        }
    }

    fun CardUiState.toDomainCard(): Card? {
        if (!checkValidCardNumber(this.cardNumber)) return null
        if (!checkValidPassword(this.password)) return null
        val expireDateStatus = getExpireDateStatus(expireDate)
        if (expireDateStatus !is ExpireDateStatus.Valid) return null

        return Card(
            cardNumber = this.cardNumber,
            expireDate = expireDateStatus.yearMonth,
            ownerName = this.ownerName.ifEmpty { null },
            password = this.password,
        )
    }
}
