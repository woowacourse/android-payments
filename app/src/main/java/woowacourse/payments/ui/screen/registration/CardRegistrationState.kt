package woowacourse.payments.ui.screen.registration

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.PaymentCardUiModel

@Parcelize
sealed interface CardRegistrationState : Parcelable {
    data object Register : CardRegistrationState

    data class Edit(
        val card: PaymentCardUiModel,
    ) : CardRegistrationState {
        fun isCardChanged(newCard: PaymentCardUiModel): Boolean {
            if (newCard.number.number != card.number.number) return true
            if (newCard.expirationDate.expirationDate != card.expirationDate.expirationDate) return true
            if (newCard.cardholderName.name != card.cardholderName.name) return true
            if (newCard.password.password != card.password.password) return true
            if (newCard.bankType != card.bankType) return true

            return false
        }
    }

    companion object {
        fun from(card: PaymentCardUiModel?): CardRegistrationState = if (card == null) Register else Edit(card)
    }
}
