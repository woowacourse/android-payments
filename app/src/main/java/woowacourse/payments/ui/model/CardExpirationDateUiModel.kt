package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import java.time.YearMonth
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardExpirationDateUiModel(
    val value: String = "",
    val errorMessage: String? = null,
) : Parcelable {
    init {
        require(value.isDigitsOnly())
        require(value.length <= REQUIRE_CARD_EXPIRATION_DATE_LENGTH)
    }

    fun isValid(): Boolean = !(value.length == REQUIRE_CARD_EXPIRATION_DATE_LENGTH && isInValidFutureOrCurrentMonth())

    fun toValidatedCardExpirationDateUiModel(): CardExpirationDateUiModel {
        if (this.value.length != REQUIRE_CARD_EXPIRATION_DATE_LENGTH) return this
        if (isInValidFutureOrCurrentMonth()) return this.copy(errorMessage = "유효하지 않은 입력입니다.") // 에러 문구가 어쩌다 여기까지..
        return this
    }

    private fun isInValidFutureOrCurrentMonth(now: YearMonth = YearMonth.now()): Boolean {
        val month: Int = value.take(2).toIntOrNull() ?: INVALID_INPUT
        val year: Int = value.takeLast(2).toIntOrNull() ?: INVALID_INPUT

        val yearMonth =
            runCatching { YearMonth.of(YEAR_OFFSET + year, month) }.getOrNull() ?: return true
        return yearMonth.isBefore(now)
    }

    companion object {
        const val REQUIRE_CARD_EXPIRATION_DATE_LENGTH = 4
        private const val YEAR_OFFSET = 2_000
        private const val INVALID_INPUT = Int.MIN_VALUE
    }
}
