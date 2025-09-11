package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.ExpireDateStatus
import woowacourse.payments.domain.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import woowacourse.payments.domain.ExpireDateValidationException
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.features.addcard.ExpireDateUiState

object CardMapper {
    fun getExpireDateUiState(expireDate: String): ExpireDateUiState {
        if (expireDate.isEmpty()) return ExpireDateUiState.Empty
        if (expireDate.length < ExpireDate.MAX_LENGTH_EXPIRE_DATE) return ExpireDateUiState.Typing

        val result = ExpireDate.from(expireDate)
        return result.fold(
            onSuccess = { createdExpireDate ->
                ExpireDateUiState.Valid(createdExpireDate)
            },
            onFailure = { throwable ->
                val reason = getExpireDateInvalidReason(throwable)
                ExpireDateUiState.Invalid(reason)
            },
        )
    }

    fun CardUiState.toDomainCard(): CardCreationResult {
        val cardNumber =
            CardNumber
                .create(this.cardNumber)
                .fold(
                    onSuccess = { it },
                    onFailure = { return CardCreationResult.InvalidCardNumber },
                )
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

        val password =
            runCatching { Password(this.password) }.getOrElse { return CardCreationResult.InvalidPassword }

        return CardCreationResult.Success(
            PaymentCard(
                cardNumber = cardNumber,
                expireDate = expireDate,
                ownerName = ownerName,
                password = password,
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
