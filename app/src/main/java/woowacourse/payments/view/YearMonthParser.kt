import java.time.YearMonth
import java.time.format.DateTimeFormatter

object YearMonthParser {
    private const val EXPIRED_DATE_LENGTH = 4
    private val Formatter = DateTimeFormatter.ofPattern("yyMM")

    fun parse(text: String): YearMonth {
        require(text.length == EXPIRED_DATE_LENGTH) {
            "만료일은 ${EXPIRED_DATE_LENGTH}자리여야 합니다. 입력값: $text"
        }
        return YearMonth.parse(text, Formatter)
    }

    fun isValid(text: String): Boolean = runCatching { parse(text) }.isSuccess
}
