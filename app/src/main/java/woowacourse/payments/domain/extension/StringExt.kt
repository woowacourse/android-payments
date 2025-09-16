package woowacourse.payments.domain.extension

fun String.isDigitsOnly(): Boolean = any { !it.isDigit() }
