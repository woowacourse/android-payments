package woowacourse.payments.ui.screen.addCard

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.util.BundleKeys.CARD_COMPANY_COLOR_KEY
import woowacourse.payments.ui.util.BundleKeys.CARD_COMPANY_LOGO_KEY
import woowacourse.payments.ui.util.BundleKeys.CARD_COMPANY_NAME_KEY
import woowacourse.payments.ui.util.BundleKeys.CARD_NUMBER_KEY
import woowacourse.payments.ui.util.BundleKeys.CARD_OWNER_KEY
import woowacourse.payments.ui.util.BundleKeys.EXPIRED_KEY
import woowacourse.payments.ui.util.BundleKeys.PASSWORD_KEY
import woowacourse.payments.ui.util.BundleKeys.VALIDATION_ERROR_KEY

class AddCardStateHolder(
    initialState: AddCardUiState = AddCardUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun updateCardNumber(newNumber: CardNumber) {
        uiState = uiState.copy(cardNumber = newNumber)
    }

    fun updateExpired(newExpired: Expired) {
        uiState = uiState.copy(expired = newExpired)
    }

    fun updateCardOwner(newOwner: CardOwner) {
        uiState = uiState.copy(cardOwner = newOwner)
    }

    fun updatePassword(newPassword: Password) {
        uiState = uiState.copy(password = newPassword)
    }

    fun updateBank(newBank: BankUiModel) {
        uiState = uiState.copy(bankUiModel = newBank)
    }

    fun validateAll() {
        uiState = uiState.validate()
    }

    companion object {
        val saver: Saver<AddCardStateHolder, Bundle> =
            Saver(
                save = { holder ->
                    bundleOf(
                        CARD_NUMBER_KEY to holder.uiState.cardNumber.value,
                        EXPIRED_KEY to holder.uiState.expired.value,
                        CARD_OWNER_KEY to holder.uiState.cardOwner.value,
                        PASSWORD_KEY to holder.uiState.password.value,
                        CARD_COMPANY_NAME_KEY to holder.uiState.bankUiModel.name,
                        CARD_COMPANY_COLOR_KEY to holder.uiState.bankUiModel.color,
                        CARD_COMPANY_LOGO_KEY to holder.uiState.bankUiModel.logoRes,
                        VALIDATION_ERROR_KEY to ArrayList(holder.uiState.errors.map { it.name }),
                    )
                },
                restore = { bundle ->
                    val errors =
                        bundle
                            .getStringArrayList(VALIDATION_ERROR_KEY)
                            ?.mapNotNull { runCatching { AddCardError.valueOf(it) }.getOrNull() }
                            ?.toSet() ?: emptySet()

                    val restoredState =
                        AddCardUiState(
                            cardNumber = CardNumber(bundle.getString(CARD_NUMBER_KEY) ?: ""),
                            expired = Expired(bundle.getString(EXPIRED_KEY) ?: ""),
                            cardOwner = CardOwner(bundle.getString(CARD_OWNER_KEY) ?: ""),
                            password = Password(bundle.getString(PASSWORD_KEY) ?: ""),
                            bankUiModel =
                                BankUiModel(
                                    name =
                                        bundle.getString(CARD_COMPANY_NAME_KEY)
                                            ?: "",
                                    color = bundle.getInt(CARD_COMPANY_COLOR_KEY),
                                    logoRes = bundle.getInt(CARD_COMPANY_LOGO_KEY),
                                ),
                            errors = errors,
                        )
                    AddCardStateHolder(restoredState)
                },
            )
    }
}
