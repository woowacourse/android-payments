package woowacourse.payments.ui.features.addcard

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_CARD_NUMBER
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_EXPIRE_DATE
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_PASSWORD
import woowacourse.payments.ui.features.addcard.components.ExpireDateStatus
import java.time.YearMonth

object CardMapper {
    private fun checkValidCardNumber(cardNumber: String): Boolean =
        cardNumber.length == MAX_LENGTH_CARD_NUMBER && cardNumber.all(Char::isDigit)

    private fun checkValidExpireDate(expireDate: String): Boolean = getExpireDateStatus(expireDate) == ExpireDateStatus.Valid

    private fun checkValidPassword(password: String): Boolean = password.length == MAX_LENGTH_PASSWORD && password.all(Char::isDigit)

    private fun checkValidCardUiState(cardUiState: CardUiState): Boolean =
        checkValidCardNumber(cardUiState.cardNumber) && checkValidExpireDate(cardUiState.expireDate) &&
            checkValidPassword(
                cardUiState.password,
            )

    fun getExpireDateStatus(expireDate: String): ExpireDateStatus {
        if (expireDate.length != MAX_LENGTH_EXPIRE_DATE) return ExpireDateStatus.Typing
        val mm = expireDate.take(2).toIntOrNull() ?: return ExpireDateStatus.InvalidFormat
        val yy = expireDate.drop(2).toIntOrNull() ?: return ExpireDateStatus.InvalidFormat
        if (mm !in 1..12) return ExpireDateStatus.InvalidMonth
        val year = 2000 + yy
        val ym = YearMonth.of(year, mm)
        return if (ym >= YearMonth.now()) ExpireDateStatus.Valid else ExpireDateStatus.Expired
    }

    fun CardUiState.toDomainCard(): Card? {
        if (!checkValidCardUiState(this)) return null

        val mm = expireDate.take(2).toInt()
        val yy = expireDate.drop(2).toInt()

        return Card(
            cardNumber = this.cardNumber,
            expireDate = YearMonth.of(2000 + yy, mm),
            ownerName = this.ownerName.ifEmpty { null },
            password = this.password,
        )
    }
}
