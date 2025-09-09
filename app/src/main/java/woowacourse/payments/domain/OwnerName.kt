package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class OwnerName(
    val value: String?,
) : Parcelable {
    init {
        require(checkValidOwnerName(value)) {
            "카드 소유자 이름이 유효하지 않습니다."
        }
    }

    private fun checkValidOwnerName(cardNumber: String?): Boolean {
        if (cardNumber == null) return true
        return cardNumber.length <= MAX_LENGTH_OWNER_NAME
    }

    companion object {
        const val MAX_LENGTH_OWNER_NAME = 30
    }
}
