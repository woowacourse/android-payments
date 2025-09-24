package woowacourse.payments.ui.newcard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.common.model.CardUiModel

@Parcelize
sealed interface CardUpdateType : Parcelable {
    data object Add : CardUpdateType

    data class Edit(
        val card: CardUiModel,
    ) : CardUpdateType
}
