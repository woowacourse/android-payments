package woowacourse.payments.ui.extension

fun IntRange.coerceInLength(length: Int): IntRange = (first.coerceAtMost(length)..last.coerceAtMost(length - 1))
