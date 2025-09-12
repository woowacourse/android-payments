package woowacourse.payments.domain.card.exception

sealed class OwnerNamerException : IllegalArgumentException() {
    data object InvalidLength : OwnerNamerException()
}
