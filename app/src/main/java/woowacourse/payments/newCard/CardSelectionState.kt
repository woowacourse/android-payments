package woowacourse.payments.newCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardCompany

class CardSelectionState(
    selectedCompany: CardCompany = CardCompany.NOT_SELECTED,
    isShowBottomSheet: Boolean = true
) {
    var selectedCompany by mutableStateOf(selectedCompany)
    var isShowBottomSheet by mutableStateOf(isShowBottomSheet)
}