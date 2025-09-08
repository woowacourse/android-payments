package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.Expired
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun ExpiredInputField(
    expired: Expired?,
    onExpiredChange: (Expired?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    val transformation =
        remember { ExpiredVisualTransformation(groupSize = 2, delimiter = " / ") }

    OutlinedTextField(
        value = expired?.value ?: "",
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }.take(4)
            val newExpired = Expired(filteredText)
            onExpiredChange(newExpired)
        },
        modifier =
            modifier.semantics {
                this.contentDescription = "Expired Input Field"
            },
        label = { Text(text = stringResource(R.string.expired_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_placeholder),
                color = Color.LightGray,
            )
        },
        isError = showValidationError && (expired?.isValid != true),
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun ExpiredInputPreview() {
    AndroidpaymentsTheme {
        ExpiredInputField(
            expired = null,
            onExpiredChange = { },
        )
    }
}
