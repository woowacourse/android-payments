package woowacourse.payments.ui

import androidx.annotation.StringRes

sealed interface CardListUiEvent {
    data class ShowToast(
        @StringRes val messageId: Int,
    ) : CardListUiEvent
}
