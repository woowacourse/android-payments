package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardExpirationDate.Companion.REQUIRE_CARD_EXPIRATION_DATE_LENGTH

@Parcelize
data class CardExpirationDateUiModel(
    val value: String = "",
) : Parcelable {
    init {
        require(value.isDigitsOnly())
        require(value.length <= REQUIRE_CARD_EXPIRATION_DATE_LENGTH)
    }

    fun isValid(): Boolean = value.length == REQUIRE_CARD_EXPIRATION_DATE_LENGTH
}
