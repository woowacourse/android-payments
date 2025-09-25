package woowacourse.payments.ui.card.register

import androidx.compose.ui.graphics.toArgb
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.color
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.formatExpirationDate
import woowacourse.payments.ui.model.formatUiCardNumber
import woowacourse.payments.ui.theme.DEFAULT_CARD_COLOR

data class RegisterCardUiState(
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cardHolderName: String = "",
    val password: String = "",
    val selectedBank: Bank? = null,
    val showBottomSheet: Boolean = true,
    val toastMessage: String? = null,
)

fun RegisterCardUiState.toUiModel(): CardUiModel =
    CardUiModel(
        id = -1L,
        number = cardNumber,
        maskedNumber = formatUiCardNumber(cardNumber),
        expirationDate = expirationDate,
        formattedExpirationDate = formatExpirationDate(expirationDate),
        cardHolderName = cardHolderName,
        bankName = selectedBank?.name.orEmpty(),
        bankColor =
            selectedBank?.color()?.toArgb()?.toLong()
                ?: DEFAULT_CARD_COLOR.toArgb().toLong(),
    )
