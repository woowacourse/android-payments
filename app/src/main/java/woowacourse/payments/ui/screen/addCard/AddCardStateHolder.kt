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
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.util.BundleKeys.CARD_COMPANY_KEY
import woowacourse.payments.ui.util.BundleKeys.CARD_NUMBER_KEY
import woowacourse.payments.ui.util.BundleKeys.CARD_OWNER_KEY
import woowacourse.payments.ui.util.BundleKeys.EXPIRED_KEY
import woowacourse.payments.ui.util.BundleKeys.PASSWORD_KEY
import woowacourse.payments.ui.util.BundleKeys.VALIDATION_ERROR_KEY
import woowacourse.payments.ui.util.getParcelableArrayListCompat
import woowacourse.payments.ui.util.getParcelableExtraCompat

class AddCardStateHolder(
    initialState: AddCardUiState = AddCardUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun updateCardNumber(newNumber: String) {
        uiState = uiState.copy(cardNumber = newNumber)
    }

    fun updateExpired(newExpired: String) {
        uiState = uiState.copy(expired = newExpired)
    }

    fun updateCardOwner(newOwner: String) {
        uiState = uiState.copy(cardOwner = newOwner)
    }

    fun updatePassword(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
        validate()
    }

    fun updateBank(newBank: BankUiModel) {
        uiState = uiState.copy(bankUiModel = newBank)
    }

    fun validate() {
        val errors = mutableSetOf<AddCardError>()
        val cardNumberValue = uiState.cardNumber
        val expiredValue = uiState.expired
        val ownerValue = uiState.cardOwner
        val passwordValue = uiState.password

        if (!CardNumber(cardNumberValue).isValid) errors.add(AddCardError.CARD_NUMBER_INVALID)
        if (!Expired(expiredValue).isValid) errors.add(AddCardError.EXPIRED_INVALID)
        if (!CardOwner(ownerValue).isValid) errors.add(AddCardError.OWNER_INVALID)
        if (!Password(passwordValue).isValid) errors.add(AddCardError.PASSWORD_INVALID)

        uiState = uiState.copy(errors = errors, submitted = true)
    }

    fun toCardUiModel(): CardUiModel =
        CardUiModel(
            bankUiModel = uiState.bankUiModel,
            number = uiState.cardNumber,
            expired = uiState.expired,
            owner = uiState.cardOwner,
        )

    companion object {
        val saver: Saver<AddCardStateHolder, Bundle> =
            Saver(
                save = { holder ->
                    bundleOf(
                        CARD_NUMBER_KEY to holder.uiState.cardNumber,
                        EXPIRED_KEY to holder.uiState.expired,
                        CARD_OWNER_KEY to holder.uiState.cardOwner,
                        PASSWORD_KEY to holder.uiState.password,
                        CARD_COMPANY_KEY to holder.uiState.bankUiModel,
                        VALIDATION_ERROR_KEY to ArrayList(holder.uiState.errors),
                    )
                },
                restore = { bundle ->
                    val restoredState =
                        AddCardUiState(
                            cardNumber = bundle.getString(CARD_NUMBER_KEY) ?: "",
                            expired = bundle.getString(EXPIRED_KEY) ?: "",
                            cardOwner = bundle.getString(CARD_OWNER_KEY) ?: "",
                            password = bundle.getString(PASSWORD_KEY) ?: "",
                            bankUiModel =
                                bundle.getParcelableExtraCompat(CARD_COMPANY_KEY) ?: BankUiModel(
                                    "wootech",
                                    0,
                                    0,
                                ),
                            errors =
                                bundle
                                    .getParcelableArrayListCompat<AddCardError>(
                                        VALIDATION_ERROR_KEY,
                                    )?.toSet()
                                    ?: emptySet(),
                        )
                    AddCardStateHolder(restoredState)
                },
            )
    }
}
