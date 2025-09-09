package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class CardNumber(
    val value: String,
) : Parcelable {
    init {
        require(checkValidCardNumber(value)) {
            "카드번호가 유효하지 않습니다."
        }
    }

    override fun toString(): String {
        val halfCardNumber = value.take(8)
        return "${halfCardNumber.take(4)} - ${halfCardNumber.takeLast(4)} - **** - ****"
    }

    private fun checkValidCardNumber(cardNumber: String): Boolean =
        cardNumber.length == MAX_LENGTH_CARD_NUMBER && cardNumber.all(Char::isDigit)

    companion object {
        const val MAX_LENGTH_CARD_NUMBER = 16
    }
}
