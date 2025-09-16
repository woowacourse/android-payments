package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardCompanyUiModel(
    val name: String,
    val color: Int,
) : Parcelable {
    val backgroundColor: Color
        get() = Color(color)

    companion object {
        fun create(
            name: String,
            color: Color,
        ): CardCompanyUiModel =
            CardCompanyUiModel(
                name = name,
                color = color.toArgb(),
            )
    }
}
