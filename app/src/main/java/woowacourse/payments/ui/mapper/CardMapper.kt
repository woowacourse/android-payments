package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.card.ExpireDateStatus
import woowacourse.payments.domain.card.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.domain.card.exception.ExpireDateException
import woowacourse.payments.domain.card.values.CardCompany
import woowacourse.payments.domain.card.values.CardNumber
import woowacourse.payments.domain.card.values.ExpireDate
import woowacourse.payments.domain.card.values.OwnerName
import woowacourse.payments.domain.card.values.Password
import woowacourse.payments.ui.components.toMaskedString
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.features.addcard.ExpireDateUiState
import woowacourse.payments.ui.features.addcard.components.CARD_NUMBER_CHUNK_SIZE
import woowacourse.payments.ui.features.addcard.components.CARD_NUMBER_SEPARATOR
import woowacourse.payments.ui.features.addcard.components.EXPIRE_DATE_CHUNK_SIZE
import woowacourse.payments.ui.features.addcard.components.EXPIRE_DATE_SEPARATOR
import woowacourse.payments.ui.model.CardCompanyUiModel
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
            cardCompanyUiModel = cardCompany.toUiModel(),
            formattedCardNumber = this.cardNumber.toMaskedString(),
            formattedExpireDate = this.expireDate.value.format(yearMonthFormatter),
            ownerName = this.ownerName.value ?: "",
        )
    }

    fun CardUiState.toPaymentCardUiModel(): PaymentCardUiModel =
        PaymentCardUiModel(
            cardCompanyUiModel = cardCompanyUiModel,
            formattedCardNumber =
                cardNumber
                    .chunked(CARD_NUMBER_CHUNK_SIZE)
                    .joinToString(CARD_NUMBER_SEPARATOR),
            formattedExpireDate =
                expireDate
                    .chunked(EXPIRE_DATE_CHUNK_SIZE)
                    .joinToString(EXPIRE_DATE_SEPARATOR),
            ownerName = ownerName,
        )

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

        if (cardCompanyUiModel == CardCompanyUiModel.UNKNOWN) {
            return CardCreationResult.UnknownCardCompany
        }

        return CardCreationResult.Success(
            PaymentCard(
                cardNumber = cardNumber,
                expireDate = expireDate,
                ownerName = ownerName,
                password = password,
                cardCompany = cardCompanyUiModel.toDomain(),
            ),
        )
    }

    fun CardCompanyUiModel.toDomain(): CardCompany =
        runCatching {
            enumValueOf<CardCompany>(this.name)
        }.getOrElse {
            CardCompany.UNKNOWN
        }

    fun CardCompany.toUiModel(): CardCompanyUiModel =
        runCatching {
            enumValueOf<CardCompanyUiModel>(this.name)
        }.getOrElse {
            CardCompanyUiModel.UNKNOWN
        }

    private fun getExpireDateInvalidReason(throwable: Throwable): ExpireDateInvalidReason =
        if (throwable is ExpireDateException) {
            throwable.reason
        } else {
            ExpireDateInvalidReason.INVALID_FORMAT
        }
}
