package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Expired
import woowacourse.payments.ui.screen.addCard.AddCardError
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun ExpiredInputField(
    onExpiredChange: (Expired) -> Unit,
    modifier: Modifier = Modifier,
    expired: Expired? = null,
    error: AddCardError? = null,
) {
    val transformation =
        remember { ExpiredVisualTransformation(groupSize = 2, delimiter = " / ") }
    val context = LocalContext.current

    OutlinedTextField(
        value = expired?.value ?: "",
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }.take(4)
            onExpiredChange(Expired(filteredText))
        },
        modifier =
            modifier.semantics {
                contentDescription = context.getString(R.string.expired_content_description)
            },
        label = { Text(text = stringResource(R.string.expired_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_placeholder),
                color = Color.LightGray,
            )
        },
        supportingText = {
            error?.let {
                Text(
                    text = stringResource(error.messageRes),
                    modifier =
                        Modifier
                            .padding(top = 4.dp)
                            .semantics {
                                contentDescription =
                                    context.getString(R.string.expired_error_content_description)
                            },
                    color = Color.Red,
                    fontSize = 12.sp,
                )
            }
        },
        isError = error != null,
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun ExpiredInputPreview() {
    AndroidpaymentsTheme {
        ExpiredInputField(
            onExpiredChange = { },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ExpiredInputErrorPreview() {
    AndroidpaymentsTheme {
        ExpiredInputField(
            onExpiredChange = { },
            error = AddCardError.EXPIRED_INVALID,
        )
    }
}
