package woowacourse.payments.domain.validator

import java.util.Calendar

class ExpirationDateValidator {
    fun validate(input: String): ValidationResult {
        // 입력 길이가 올바르지 않은 경우
        if (input.length != EXPIRATION_DATE_MAX_LENGTH) {
            return ValidationResult.Error(
                ValidationErrorType.InvalidLength(FieldType.EXPIRATION_DATE),
            )
        }

        // 입력에 숫자가 아닌 문자가 포함된 경우
        if (!input.all { it.isDigit() }) {
            return ValidationResult.Error(ValidationErrorType.InvalidCharacters)
        }

        // input 문자열을 직접 월과 연도로 분리
        val monthStr = input.take(2)
        val yearStr = input.drop(2)

        // 월(MM)이 01~12 범위에 있는지 검사
        val inputMonth =
            monthStr.toIntOrNull()
                ?: return ValidationResult.Error(ValidationErrorType.InvalidFormat)
        if (inputMonth !in 1..12) {
            return ValidationResult.Error(ValidationErrorType.InvalidFormat)
        }

        // 날짜 유효성 검사
        val currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val inputYear = yearStr.toInt()

        // 입력된 연도가 현재 연도보다 과거인 경우
        if (inputYear < currentYear) {
            return ValidationResult.Error(ValidationErrorType.ExpiredDate)
        }

        // 입력된 연도가 현재 연도와 같고, 입력된 월이 현재 월보다 과거인 경우
        if (inputYear == currentYear && inputMonth < currentMonth) {
            return ValidationResult.Error(ValidationErrorType.ExpiredDate)
        }

        return ValidationResult.Success
    }

    companion object {
        private const val EXPIRATION_DATE_MAX_LENGTH = 4
    }
}
