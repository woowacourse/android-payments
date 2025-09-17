package woowacourse.payments.ui.cards.state

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class CardsScreenUiState(
    val value: List<CardUiModel> = emptyList(),
) : Parcelable {
    @IgnoredOnParcel
    private val screenState: CardsScreenState get() = CardsScreenState.from(value.size)

    fun isVisibleRegistrationButtonInTopBar(): Boolean = screenState.isVisibleRegistrationInTopBar()

    fun isVisibleRegistrationBoxInContent(): Boolean = screenState.isVisibleRegistrationInContent()

    fun hasNoContent(): Boolean = screenState == CardsScreenState.EMPTY

    fun copyWithAddCard(newCard: CardUiModel): CardsScreenUiState = CardsScreenUiState(this.value + newCard)
}
