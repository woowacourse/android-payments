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

        if (!CardNumber(cardNumberValue).isValid) {
            errors.add(
                AddCardError.CARD_NUMBER_INVALID,
            )
        }
        if (expiredValue.isEmpty() || !Expired(expiredValue).isValid) errors.add(AddCardError.EXPIRED_INVALID)
        if (!CardOwner(ownerValue).isValid) errors.add(AddCardError.OWNER_INVALID)
        if (passwordValue.isEmpty() || !Password(passwordValue).isValid) errors.add(AddCardError.PASSWORD_INVALID)

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
                        "card_number" to holder.uiState.cardNumber,
                        "expired" to holder.uiState.expired,
                        "owner" to holder.uiState.cardOwner,
                        "password" to holder.uiState.password,
                        "bank_ui_model" to holder.uiState.bankUiModel,
                        "errors" to ArrayList(holder.uiState.errors),
                    )
                },
                restore = { bundle ->
                    val restoredState =
                        AddCardUiState(
                            cardNumber = bundle.getString("card_number") ?: "",
                            expired = bundle.getString("expired") ?: "",
                            cardOwner = bundle.getString("owner") ?: "",
                            password = bundle.getString("password") ?: "",
                            bankUiModel =
                                bundle.getParcelableExtraCompat("bank_ui_model") ?: BankUiModel(
                                    "",
                                    0,
                                    0,
                                ),
                            errors =
                                bundle.getParcelableArrayListCompat<AddCardError>("errors")?.toSet()
                                    ?: emptySet(),
                        )
                    AddCardStateHolder(restoredState)
                },
            )
    }
}
