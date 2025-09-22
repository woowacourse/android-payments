package woowacourse.payments.ui.cardRegister

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.common.model.CardCompanyUiType

@Composable
fun rememberCardRegisterState(
    cardNumber: String = "",
    expiredDate: String = "",
    ownerName: String = "",
    password: String = "",
    isShowingBottomSheet: Boolean = true,
    selectedCardCompany: CardCompanyUiType = CardCompanyUiType.NOT_SELECTED,
): CardRegisterState =
    rememberSaveable(saver = CardRegisterState.Saver) {
        CardRegisterState(
            initialCardNumber = cardNumber,
            initialExpiredDate = expiredDate,
            initialOwnerName = ownerName,
            initialPassword = password,
            initialIsShowingBottomSheet = isShowingBottomSheet,
            initialSelectedCardCompany = selectedCardCompany,
        )
    }
