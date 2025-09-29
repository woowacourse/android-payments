package woowacourse.payments.ui.cardRegister

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.common.model.CardCompanyUiType
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun rememberCardRegisterState(
    card: CardUiModel? = null,
    cardNumber: String = "",
    expiredDate: String = "",
    ownerName: String = "",
    password: String = "",
    isShowingBottomSheet: Boolean = true,
    selectedCardCompany: CardCompanyUiType = CardCompanyUiType.NOT_SELECTED,
): CardRegisterState =
    rememberSaveable(saver = CardRegisterState.Saver) {
        CardRegisterState(
            initialCardNumber = card?.number ?: cardNumber,
            initialExpiredDate = card?.expiredDate ?: expiredDate,
            initialOwnerName = card?.ownerName ?: ownerName,
            initialPassword = card?.password ?: password,
            initialIsShowingBottomSheet = isShowingBottomSheet,
            initialSelectedCardCompany = card?.cardCompany ?: selectedCardCompany,
            originalCardUiModel = card,
        )
    }
