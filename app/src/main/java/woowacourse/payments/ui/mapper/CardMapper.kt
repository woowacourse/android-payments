package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.domain.card.exception.ExpireDateException
import woowacourse.payments.domain.card.values.CardCompany
import woowacourse.payments.domain.card.values.CardNumber
import woowacourse.payments.domain.card.values.ExpireDate
import woowacourse.payments.domain.card.values.ExpireDate.Companion.create
import woowacourse.payments.domain.card.values.OwnerName
import woowacourse.payments.domain.card.values.Password
import woowacourse.payments.ui.components.toMaskedString
import woowacourse.payments.ui.features.cartinput.CardUiState
import woowacourse.payments.ui.features.cartinput.ExpireDateUiState
import woowacourse.payments.ui.features.cartinput.components.CARD_NUMBER_CHUNK_SIZE
import woowacourse.payments.ui.features.cartinput.components.CARD_NUMBER_SEPARATOR
import woowacourse.payments.ui.features.cartinput.components.EXPIRE_DATE_CHUNK_SIZE
import woowacourse.payments.ui.features.cartinput.components.EXPIRE_DATE_SEPARATOR
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.ExpireDateStatus
import woowacourse.payments.ui.model.ExpireDateStatus.Invalid.ExpireDateInvalidReason
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel.Companion.EMPTY_DB_ID
import woowacourse.payments.ui.model.PaymentCardUiModel.Companion.MAX_EXPIRE_DATE_INPUT_LENGTH
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object CardMapper {
    fun getExpireDateUiState(expireDate: String): ExpireDateUiState {
        if (expireDate.isEmpty()) return ExpireDateUiState.Empty
        if (expireDate.length < MAX_EXPIRE_DATE_INPUT_LENGTH) return ExpireDateUiState.Typing

        val result = toExpireDate(expireDate)
        return result.fold(
            onSuccess = { ExpireDateUiState.Valid },
            onFailure = { throwable ->
                val reason = getExpireDateInvalidReason(throwable)
                ExpireDateUiState.Invalid(reason)
            },
        )
    }

    fun PaymentCard.toUiModel(): PaymentCardUiModel {
        val yearMonthFormatter = DateTimeFormatter.ofPattern("MM / yy")

        return PaymentCardUiModel(
            dbId = EMPTY_DB_ID,
            cardCompanyUiModel = cardCompany.toUiModel(),
            formattedCardNumber = this.cardNumber.toMaskedString(),
            formattedExpireDate = this.expireDate.value.format(yearMonthFormatter),
            ownerName = this.ownerName.value ?: "",
        )
    }

    fun CardUiState.toPaymentCardUiModel(): PaymentCardUiModel =
        PaymentCardUiModel(
            dbId = EMPTY_DB_ID,
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
            toExpireDate(this.expireDate)
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

    private fun toExpireDate(expireDateString: String): Result<ExpireDate> {
        if (expireDateString.length != MAX_EXPIRE_DATE_INPUT_LENGTH) {
            return Result.failure(ExpireDateException(ExpireDateInvalidReason.INVALID_FORMAT))
        }

        val mm =
            expireDateString.take(2).toIntOrNull() ?: return Result.failure(
                ExpireDateException(ExpireDateInvalidReason.INVALID_FORMAT),
            )

        val yy =
            expireDateString.takeLast(2).toIntOrNull() ?: return Result.failure(
                ExpireDateException(ExpireDateInvalidReason.INVALID_FORMAT),
            )

        if (mm !in 1..12) {
            return Result.failure(ExpireDateException(ExpireDateInvalidReason.INVALID_MONTH))
        }

        val yearMonth = YearMonth.of(2000 + yy, mm)

        return create(yearMonth)
    }
}
