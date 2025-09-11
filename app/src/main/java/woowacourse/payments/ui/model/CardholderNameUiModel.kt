package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class CardholderNameUiModel(
    val value: String = "",
) : Parcelable {
    val isValid: Boolean get() = value.all { it in 'a'..'z' || it in 'A'..'Z' || it.isWhitespace() }

    @IgnoredOnParcel
    val maxNameLength: Int = MAXIMUM_NAME_LENGTH_VALUE

    init {
        require(value.length <= MAXIMUM_NAME_LENGTH_VALUE) { ERROR_INVALID_LENGTH }
    }

    fun nameOrNull(): String? = value.ifBlank { null }

    companion object {
        private const val ERROR_INVALID_LENGTH = "카드 소유자 이름은 30자를 초과할 수 없습니다."
        private const val MAXIMUM_NAME_LENGTH_VALUE = 30
    }
}
