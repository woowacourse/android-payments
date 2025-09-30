package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.OwnerName

@Parcelize
data class OwnerNameUiModel(
    val name: String = "",
) : Parcelable {
    override fun toString(): String = name

    fun toDomain(): OwnerName =
        OwnerName(
            name = name,
        )
}
