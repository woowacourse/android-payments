package woowacourse.payments.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class OwnerName(
    val value: String,
) : Parcelable {
    init {
        require(value.length in OWNER_NAME_MIN_LENGTH..OWNER_NAME_MAX_LENGTH) { OwnerNameException.OwnerNameMaxLengthException }
        requireNotNull(value.all { it.isWhitespace() }) { OwnerNameException.OwnerNameWhitespaceException }
        require(value.all { it in 'a'..'z' || it in 'A'..'Z' || it.isWhitespace() }) { OwnerNameException.OwnerNameTypeException }
    }

    companion object {
        private const val OWNER_NAME_MIN_LENGTH: Int = 1
        private const val OWNER_NAME_MAX_LENGTH: Int = 30
    }
}


