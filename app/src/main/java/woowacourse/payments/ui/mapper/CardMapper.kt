package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.card.ExpireDateStatus
import woowacourse.payments.domain.card.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.domain.card.exception.ExpireDateException
import woowacourse.payments.domain.card.values.CardNumber
import woowacourse.payments.domain.card.values.ExpireDate
import woowacourse.payments.domain.card.values.OwnerName
import woowacourse.payments.domain.card.values.Password
import woowacourse.payments.ui.components.toMaskedString
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.features.addcard.ExpireDateUiState
import woowacourse.payments.ui.model.CardCompany
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
            // TODO : 카드사 도메인 추가 민 변환 필요
            cardCompany = CardCompany.UNKNOWN,
            maskedCardNumber = this.cardNumber.toMaskedString(),
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
        if (throwable is ExpireDateException) {
            throwable.reason
        } else {
            ExpireDateInvalidReason.INVALID_FORMAT
        }
}
