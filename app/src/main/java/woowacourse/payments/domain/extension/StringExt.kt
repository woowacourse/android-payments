package woowacourse.payments.domain.extension

fun String.isDigitsOnly(): Boolean = all { it.isDigit() }
