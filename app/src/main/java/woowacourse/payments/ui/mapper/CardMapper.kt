package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDateInvalidReason
import woowacourse.payments.domain.ExpireDateStatus
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.features.addcard.CardUiState
import java.time.YearMonth

object CardMapper {
    private fun checkValidPassword(password: String): Boolean =
        password.length == PaymentCard.Companion.MAX_LENGTH_PASSWORD && password.all(Char::isDigit)

    private fun get21cYearMonth(
        yy: Int,
        mm: Int,
    ): YearMonth = YearMonth.of(2000 + yy, mm)

    fun getExpireDateStatus(expireDate: String): ExpireDateStatus {
        if (expireDate.isEmpty()) return ExpireDateStatus.Empty
        if (expireDate.length < PaymentCard.Companion.MAX_LENGTH_EXPIRE_DATE) return ExpireDateStatus.Typing
        val mm =
            expireDate.take(2).toIntOrNull() ?: return ExpireDateStatus.Invalid(
                ExpireDateInvalidReason.INVALID_FORMAT,
            )
        val yy =
            expireDate.drop(2).toIntOrNull() ?: return ExpireDateStatus.Invalid(
                ExpireDateInvalidReason.INVALID_FORMAT,
            )
        if (mm !in 1..12) return ExpireDateStatus.Invalid(ExpireDateInvalidReason.INVALID_MONTH)
        val typedYearMonth = get21cYearMonth(yy, mm)
        return if (typedYearMonth >= YearMonth.now()) {
            ExpireDateStatus.Valid(typedYearMonth)
        } else {
            ExpireDateStatus.Invalid(ExpireDateInvalidReason.EXPIRED)
        }
    }

    fun CardUiState.toDomainCard(): CardCreationResult {
        val cardNumber =
            runCatching { CardNumber(this.cardNumber) }
                .getOrElse {
                    return CardCreationResult.InvalidCardNumber
                }
        val expireDateStatus = getExpireDateStatus(expireDate)
        if (expireDateStatus !is ExpireDateStatus.Valid) {
            return CardCreationResult.InvalidExpireDate(
                expireDateStatus,
            )
        }
        if (!checkValidPassword(this.password)) return CardCreationResult.InvalidPassword

        return CardCreationResult.Success(
            PaymentCard(
                cardNumber = cardNumber,
                expireDate = expireDateStatus.yearMonth,
                ownerName = this.ownerName.ifEmpty { null },
                password = this.password,
            ),
        )
    }
}
