package woowacourse.payments.ui.newcard.state

import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.ui.newcard.uiModel.CardCompanyUiModel
import woowacourse.payments.ui.newcard.uiModel.NewCardMode
import woowacourse.payments.ui.newcard.uiModel.toUiModel

 data class CardUiState(
    val card: Card? = null,
    val cardCompany: CardCompany? = null,
    val number: String = "",
    val expirationDate: String = "",
    val ownerName: String = "",
    val password: String = "",

    val cardErrorMessage: String? = null,
    val numberErrorMessage: String? = null,
    val expirationDateErrorMessage: String? = null,
    val ownerNameErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,

    val isBottomSheetOpen: Boolean = true,

     val isPossibleAddCard: Boolean = false,
     val isChangeCardCompany: Boolean = false,
     val isChangeNumber: Boolean = false,
     val isChangeExpirationDate: Boolean = false,
     val isChangeOwnerName: Boolean = false,
     val isPassword: Boolean = false,
 ) {
    val cardCompanyUiModel: CardCompanyUiModel
        get() = cardCompany?.toUiModel() ?: CardCompanyUiModel.Default()
 }