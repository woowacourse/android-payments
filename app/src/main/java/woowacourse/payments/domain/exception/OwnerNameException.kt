package woowacourse.payments.domain.exception

sealed class OwnerNameException(
    override val message: String
) : Throwable() {
    data object OwnerNameMaxLengthException : OwnerNameException("소유자의 이름의 길이는 1 ~ 30자 사이이다.")
    data object OwnerNameWhitespaceException : OwnerNameException("소유자의 이름은 공백으로만 이루어질 수 없습니다.")
    data object OwnerNameTypeException : OwnerNameException("소유자의 이름에는 문자와 공백만 가능하다.")
}