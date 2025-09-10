package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.exception.CardNumberException

@Parcelize
@JvmInline
value class CardNumber(
    val value: String,
) : Parcelable {
    init {
        require(value.length == CARD_NUMBER_LENGTH) { CardNumberException.CardNumberLengthException.message }
        require(value.all(Char::isDigit)) { CardNumberException.CardNumberTypeException.message }
    }

    companion object {
        private const val CARD_NUMBER_LENGTH: Int = 16
    }
}

