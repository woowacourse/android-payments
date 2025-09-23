package woowacourse.payments.ui.cardform.model

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.common.model.CardUiModel

const val CARD_NUMBER_MAX: Int = 16
const val EXPIRY_MAX: Int = 4
const val CARD_HOLDER_MAX: Int = 30
const val PIN_MAX: Int = 4

const val CARD_NUMBER_SEPARATOR: String = " - "

class CardFormUiStateHolder(
    original: CardUiModel? = null,
    cardNumberInit: String = "",
    expiryInit: String = "",
    holderInit: String = "",
    pinInit: String = "",
    bankInit: BankType = BankType.NOT_SELECTED,
    isBankSheetOpenInit: Boolean = false,
) {
    private val originalCard: CardUiModel? = original

    var cardNumber by mutableStateOf(cardNumberInit)
        private set
    var expiry by mutableStateOf(expiryInit)
        private set
    var holder by mutableStateOf(holderInit)
        private set
    var pin by mutableStateOf(pinInit)
        private set
    var isBankSheetOpen by mutableStateOf(isBankSheetOpenInit)
        private set

    fun updateCardNumber(input: String) {
        val onlyDigits = input.filter { it.isDigit() }.take(CARD_NUMBER_MAX)
        cardNumber = onlyDigits
    }

    fun updateExpiry(input: String) {
        val onlyDigits = input.filter { it.isDigit() }.take(EXPIRY_MAX)
        expiry = onlyDigits
    }

    fun updateHolder(input: String) {
        val onlyLettersAndSpace = input.filter { it.isLetter() || it == ' ' }.take(CARD_HOLDER_MAX)
        holder = onlyLettersAndSpace
    }

    fun updatePin(input: String) {
        val onlyDigits = input.filter { it.isDigit() }.take(PIN_MAX)
        pin = onlyDigits
    }

    var selectedBank by mutableStateOf(bankInit)
        private set

    val canSave: Boolean
        get() =
            (cardNumber.length == CARD_NUMBER_MAX) &&
                (expiry.length == EXPIRY_MAX) &&
                (pin.length == PIN_MAX) &&
                (selectedBank != BankType.NOT_SELECTED) &&
                isModified

    private val isModified: Boolean
        get() =
            originalCard == null ||
                originalCard.numberDigits != cardNumber ||
                originalCard.expiry != expiry ||
                originalCard.holder != holder ||
                originalCard.bankType != selectedBank

    fun createCardUiModel(): CardUiModel =
        CardUiModel(
            id = originalCard?.id,
            numberDigits = cardNumber,
            expiry = expiry,
            holder = holder,
            bankType = selectedBank,
        )

    fun updateBank(bank: BankType) {
        selectedBank = bank
    }

    fun updateBankSheet(isOpen: Boolean) {
        isBankSheetOpen = isOpen
    }

    @Parcelize
    data class Saved(
        val cardNumber: String,
        val expiry: String,
        val holder: String,
        val pin: String,
        val bank: String,
        val isBankSheetOpen: Boolean,
    ) : Parcelable

    companion object {
        val Saver: Saver<CardFormUiStateHolder, Saved> =
            Saver(
                save = { holder ->
                    Saved(
                        cardNumber = holder.cardNumber,
                        expiry = holder.expiry,
                        holder = holder.holder,
                        pin = holder.pin,
                        bank = holder.selectedBank.name,
                        isBankSheetOpen = holder.isBankSheetOpen,
                    )
                },
                restore = { saved ->
                    CardFormUiStateHolder(
                        cardNumberInit = saved.cardNumber,
                        expiryInit = saved.expiry,
                        holderInit = saved.holder,
                        pinInit = saved.pin,
                        bankInit = BankType.valueOf(saved.bank),
                        isBankSheetOpenInit = saved.isBankSheetOpen,
                    )
                },
            )
    }
}

@Composable
fun rememberCardFormState(): CardFormUiStateHolder =
    rememberSaveable(saver = CardFormUiStateHolder.Saver) {
        CardFormUiStateHolder()
    }
