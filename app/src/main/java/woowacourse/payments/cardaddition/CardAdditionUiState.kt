package woowacourse.payments.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.BankType

@Parcelize
data class CardAdditionUiState(
    val cardNumber: String = "",
    val isValidCardNumber: Boolean = false,
    val expiredDate: String = "",
    val isValidExpiredDate: Boolean = false,
    val holder: String = "",
    val holderMaxLength: Int = 30,
    val password: String = "",
    val isValidPassword: Boolean = false,
    val bankType: BankType? = null,
    val isBankSelected: Boolean = false,
    val canAddCard: Boolean = false,
) : Parcelable
