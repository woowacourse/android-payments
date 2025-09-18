package woowacourse.payments.ui.screen

import woowacourse.payments.domain.model.CardCompanyType

data class AddCardScreenUiState(
    val formState: AddCardFormState = AddCardFormState(),
    val showSheet: Boolean = true,
    val selectedCardCompanyType: CardCompanyType = CardCompanyType.NOT_SELECTED,
)
