package woowacourse.payments.ui.screen.registration

import woowacourse.payments.ui.common.StringResWithParams

sealed interface CardRegistrationScreenUiEvent {
    data class ShowSnackbar(
        val message: StringResWithParams,
    ) : CardRegistrationScreenUiEvent
}
