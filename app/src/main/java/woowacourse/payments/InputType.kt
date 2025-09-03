package woowacourse.payments

sealed class InputType {
    data object Normal : InputType()
    data object Password : InputType()
    data object CardNumber : InputType()
    data object ExpiryDate : InputType()
}

fun InputType.format(raw: String): String = when (this) {
    is InputType.CardNumber -> {
        val digits = raw.filter { it.isDigit() }.take(16)
        digits.chunked(4).joinToString(" - ")
    }

    is InputType.ExpiryDate -> {
        val digits = raw.filter { it.isDigit() }.take(4)
        if (digits.length <= 2) digits else digits.chunked(2).joinToString(" / ")
    }

    is InputType.Password -> {
        raw.filter { it.isDigit() }.take(4)
    }

    else -> raw
}
