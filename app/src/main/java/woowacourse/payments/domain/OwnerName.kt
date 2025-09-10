package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerName(
    val name: String = "",
) : Parcelable {
    override fun toString(): String = name

    companion object {
        const val CARD_OWNER_MAX_LENGTH = 30

        fun fromRawInput(name: String): OwnerName {
            val newName = name.take(CARD_OWNER_MAX_LENGTH)
            return OwnerName(newName)
        }
    }
}
