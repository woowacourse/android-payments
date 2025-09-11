package woowacourse.payments.domain.exception

sealed class OwnerNamerException : IllegalArgumentException() {
    data object InvalidLength : OwnerNamerException()
}
