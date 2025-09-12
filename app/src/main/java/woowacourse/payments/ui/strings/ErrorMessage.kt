package woowacourse.payments.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.domain.validator.ValidationErrorType

@Composable
fun getErrorMessage(errorType: ValidationErrorType): String =
    when (errorType) {
        ValidationErrorType.InvalidCardNumberLength -> stringResource(R.string.error_invalid_card_number_length)
        ValidationErrorType.InvalidUserNameLength -> stringResource(R.string.error_invalid_username_length)
        ValidationErrorType.InvalidPasswordLength -> stringResource(R.string.error_invalid_password_length)

        ValidationErrorType.InvalidFormat -> stringResource(R.string.error_invalid_format)
        ValidationErrorType.ExpiredDate -> stringResource(R.string.error_expired_date)
        ValidationErrorType.InvalidCharacters -> stringResource(R.string.error_invalid_characters)
    }
