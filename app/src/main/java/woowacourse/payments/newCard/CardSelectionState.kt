package woowacourse.payments.newCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardCompany

class CardSelectionState {
    var selectedCompany by mutableStateOf(CardCompany.NOT_SELECTED)
    var isShowBottomSheet by mutableStateOf(true)
}
