package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
data class CardExpirationDateUiModel(
    val value: String = "",
) : Parcelable {
    val isValid: Boolean get() = value.length <= REQUIRE_CARD_EXPIRATION_DATE_LENGTH
    val isError: Boolean
        get() = value.length == REQUIRE_CARD_EXPIRATION_DATE_LENGTH && isInValidFutureOrCurrentMonth()

    init {
        require(value.isDigitsOnly()) { ERROR_INVALID_INPUT }
    }

    private fun isInValidFutureOrCurrentMonth(now: YearMonth = YearMonth.now()): Boolean {
        val month: Int = value.take(2).toIntOrNull() ?: INVALID_INPUT
        val year: Int = value.takeLast(2).toIntOrNull() ?: INVALID_INPUT

        val yearMonth =
            runCatching { YearMonth.of(YEAR_OFFSET + year, month) }.getOrNull() ?: return true
        return yearMonth.isBefore(now)
    }

    companion object {
        private const val ERROR_INVALID_INPUT = "유효하지 않은 입력입니다."
        private const val YEAR_OFFSET = 2_000
        private const val INVALID_INPUT = Int.MIN_VALUE
        private const val REQUIRE_CARD_EXPIRATION_DATE_LENGTH = 4
    }
}
