package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Password

@Parcelize
data class PasswordUiModel(
    val password: String = "",
) : Parcelable {
    override fun toString(): String = password

    fun toDomain(): Password =
        Password(
            password = password,
        )
}
