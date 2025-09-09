package woowacourse.payments.ui.text

private const val CARD_VISIBLE_PREFIX = 8
private const val CARD_GROUP_SIZE = 4
private const val CARD_GROUP_SEP = " - "
private const val MASK_CHAR = '*'

fun String.maskAndFormatCardNumber(): String {
    if (this.isEmpty()) return ""

    val visible = this.take(CARD_VISIBLE_PREFIX)
    val masked = MASK_CHAR.toString().repeat((this.length - visible.length).coerceAtLeast(0))
    return (visible + masked).chunked(CARD_GROUP_SIZE).joinToString(CARD_GROUP_SEP)
}

private const val EXP_GROUP_SIZE = 2
private const val EXP_SEP = " / "

fun String.formatExpirationDate(): String = this.chunked(EXP_GROUP_SIZE).joinToString(EXP_SEP)
