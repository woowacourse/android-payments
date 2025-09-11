package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.ExpireDateStatus
import woowacourse.payments.domain.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import woowacourse.payments.domain.ExpireDateValidationException
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.components.toMaskedString
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.features.addcard.ExpireDateUiState
import woowacourse.payments.ui.model.PaymentCardUiModel
import java.time.format.DateTimeFormatter

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

    fun PaymentCard.toUiModel(): PaymentCardUiModel {
        val yearMonthFormatter = DateTimeFormatter.ofPattern("MM / yy")

        return PaymentCardUiModel(
            maskedCardNumber = this.cardNumber.toMaskedString(), // UI 포맷팅 확장 함수 사용
            formattedExpireDate = this.expireDate.value.format(yearMonthFormatter),
            ownerName = this.ownerName.value ?: "",
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
            OwnerName
                .create(this.ownerName)
                .fold(
                    onSuccess = { it },
                    onFailure = { return CardCreationResult.InvalidOwnerName },
                )

        val password =
            Password.create(this.password).fold(
                onSuccess = { it },
                onFailure = { return CardCreationResult.InvalidPassword },
            )

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
