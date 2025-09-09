@file:Suppress("ktlint:standard:filename")

package woowacourse.payments.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.domain.validator.FieldType
import woowacourse.payments.domain.validator.ValidationErrorType

@Composable
fun getErrorMessage(errorType: ValidationErrorType): String =
    when (errorType) {
        is ValidationErrorType.InvalidLength -> {
            when (errorType.fieldType) {
                FieldType.CARD_NUMBER -> stringResource(R.string.error_invalid_card_number_length)
                FieldType.EXPIRATION_DATE -> stringResource(R.string.error_invalid_expiration_length)
                FieldType.USER_NAME -> stringResource(R.string.error_invalid_username_length)
                FieldType.PASSWORD -> stringResource(R.string.error_invalid_password_length)
            }
        }

        ValidationErrorType.InvalidFormat -> stringResource(R.string.error_invalid_format)
        ValidationErrorType.ExpiredDate -> stringResource(R.string.error_expired_date)
        ValidationErrorType.InvalidCharacters -> stringResource(R.string.error_invalid_characters)
    }
