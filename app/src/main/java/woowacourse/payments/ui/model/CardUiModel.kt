package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.screen.cards.CardsUiState
import java.lang.Character.isDigit

@Parcelize
data class CardUiModel(
    val number: String,
    val expiredDate: String,
    val ownerName: String,
) : Parcelable {
    fun formatCardNumber(): String {
        val visiblePart = number.take(CARD_NUMBER_VISIBLE_LENGTH)
        val maskedPart = CARD_NUMBER_MASKING_SIGN.repeat(CARD_NUMBER_VISIBLE_LENGTH)
        return (visiblePart + maskedPart)
            .chunked(CARD_NUMBER_GROUP_SIZE)
            .joinToString(CARD_NUMBER_DELIMITER)
    }

    fun formatExpiredDate(): String =
        expiredDate.filter(::isDigit)
            .chunked(CARD_DATE_GROUP_SIZE)
            .joinToString(CARD_DATE_DELIMITER)

    companion object {
        private const val CARD_NUMBER_MASKING_SIGN = "*"
        private const val CARD_NUMBER_DELIMITER = " - "
        private const val CARD_DATE_DELIMITER = " / "
        private const val CARD_NUMBER_VISIBLE_LENGTH = 8
        private const val CARD_NUMBER_GROUP_SIZE = 4
        private const val CARD_DATE_GROUP_SIZE = 2
    }
}

fun List<CardUiModel>.toUiState(): CardsUiState =
    when {
        isEmpty() -> CardsUiState.Empty
        size == 1 -> CardsUiState.SingleCard(first())
        else -> CardsUiState.MultipleCards(this)
    }
