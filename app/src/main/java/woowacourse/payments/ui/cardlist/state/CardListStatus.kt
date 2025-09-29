package woowacourse.payments.ui.cardlist.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel


sealed interface CardListUiStatus : Parcelable {
    @Parcelize
    data object EmptyCardList : CardListUiStatus

    @Parcelize
    data class OneCardList(val card: CardUiModel) : CardListUiStatus

    @Parcelize
    data class MultiCardList(val card: List<CardUiModel>) : CardListUiStatus
}