package woowacourse.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.list.CardUiModel

class CardSelectionState {
    var selectedCompany by mutableStateOf(CardCompany.NOT_SELECTED)
    val cardState: PaymentCardState
        get() = if (selectedCompany == CardCompany.NOT_SELECTED) {
            PaymentCardState.Empty
        } else {
            PaymentCardState.CardInfo(
                CardUiModel(
                    company = selectedCompany,
                    number = "",
                    name = null,
                    expiry = "",
                    password = ""
                )
            )
        }
}