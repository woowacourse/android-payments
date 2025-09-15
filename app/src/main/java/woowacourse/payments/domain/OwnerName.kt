package woowacourse.payments.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.exception.OwnerNameException

@Parcelize
@JvmInline
value class OwnerName(
    val value: String?,
) : Parcelable {
    init {
        if (value != null) {
            require(value.length <= OWNER_NAME_MAX_LENGTH) { OwnerNameException.OwnerNameMaxLengthException.message }
            require(value.all { it in 'a'..'z' || it in 'A'..'Z' || it.isWhitespace() }) { OwnerNameException.OwnerNameTypeException.message }
        }
    }

    companion object {
        private const val OWNER_NAME_MIN_LENGTH: Int = 1
        private const val OWNER_NAME_MAX_LENGTH: Int = 30
    }
}


