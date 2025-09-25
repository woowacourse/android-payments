package woowacourse.payments.domain

enum class CardExpirationErrorCode {
    INVALID_LENGTH,
    INVALID_FORMAT,
    PAST_DATE,
}
