package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.ExpireDateInvalidReason
import woowacourse.payments.domain.ExpireDateStatus
import woowacourse.payments.domain.ExpireDateValidationException
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.features.addcard.CardUiState

object CardMapper {
    private fun checkValidPassword(password: String): Boolean =
        password.length == PaymentCard.Companion.MAX_LENGTH_PASSWORD && password.all(Char::isDigit)

    fun getExpireDateStatus(expireDate: String): ExpireDateStatus {
        if (expireDate.isEmpty()) return ExpireDateStatus.Empty
        if (expireDate.length < ExpireDate.MAX_LENGTH_EXPIRE_DATE) return ExpireDateStatus.Typing

        val result = ExpireDate.from(expireDate)
        return result.fold(
            onSuccess = { createdExpireDate ->
                ExpireDateStatus.Valid(createdExpireDate.value)
            },
            onFailure = { throwable ->
                val reason = getExpireDateInvalidReason(throwable)
                ExpireDateStatus.Invalid(reason)
            },
        )
    }

    fun CardUiState.toDomainCard(): CardCreationResult {
        val cardNumber =
            runCatching { CardNumber(cardNumber) }
                .getOrElse {
                    return CardCreationResult.InvalidCardNumber
                }
        val expireDate =
            ExpireDate
                .from(this.expireDate)
                .getOrElse { throwable ->
                    return CardCreationResult.InvalidExpireDate(
                        ExpireDateStatus.Invalid(
                            getExpireDateInvalidReason(
                                throwable,
                            ),
                        ),
                    )
                }

        val ownerName =
            runCatching { OwnerName(this.ownerName) }.getOrElse { return CardCreationResult.InvalidOwnerName }

        if (!checkValidPassword(this.password)) return CardCreationResult.InvalidPassword

        return CardCreationResult.Success(
            PaymentCard(
                cardNumber = cardNumber,
                expireDate = expireDate,
                ownerName = ownerName,
                password = this.password,
            ),
        )
    }

    private fun getExpireDateInvalidReason(throwable: Throwable): ExpireDateInvalidReason =
        if (throwable is ExpireDateValidationException) {
            throwable.reason
        } else {
            ExpireDateInvalidReason.INVALID_FORMAT
        }
}
