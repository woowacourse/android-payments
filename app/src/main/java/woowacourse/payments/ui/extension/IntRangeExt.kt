package woowacourse.payments.ui.extension

fun IntRange.coerceAtMost(length: Int): IntRange = (first.coerceAtMost(length)..last.coerceAtMost(length - 1))
