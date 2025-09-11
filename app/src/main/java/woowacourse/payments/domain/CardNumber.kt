package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.exception.CardNumberException

@JvmInline
@Parcelize
value class CardNumber private constructor(
    val value: String,
) : Parcelable {
    init {
        if (value.length != MAX_LENGTH_CARD_NUMBER) {
            throw CardNumberException.InvalidLength
        }
        if (!value.all(Char::isDigit)) {
            throw CardNumberException.NotDigit
        }
    }

    override fun toString(): String {
        val halfCardNumber = value.take(8)
        return "${halfCardNumber.take(4)} - ${halfCardNumber.takeLast(4)} - **** - ****"
    }

    companion object {
        const val MAX_LENGTH_CARD_NUMBER = 16

        fun create(value: String): Result<CardNumber> =
            runCatching {
                CardNumber(value)
            }
    }
}
