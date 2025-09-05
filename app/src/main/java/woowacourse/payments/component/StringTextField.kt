package woowacourse.payments.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray200

@Composable
fun StringTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 30,
) {
    val textFieldValue = TextFieldValue(text = value, selection = TextRange(value.length))

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { input ->
            onValueChange(input.text.take(maxLength))
        },
        label = { Text(stringResource(R.string.label_owner), color = Gray200) },
        placeholder = { Text(stringResource(R.string.placeholder_owner)) },
        supportingText = {
            Text(
                text = "${value.length} / $maxLength",
                color = Gray200,
                modifier = Modifier,
                textAlign = TextAlign.End,
            )
        },
        modifier = modifier.padding(start = 24.dp, end = 24.dp, bottom = 18.dp),
    )
}
