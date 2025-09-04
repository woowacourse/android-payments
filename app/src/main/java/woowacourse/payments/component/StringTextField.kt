package woowacourse.payments.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.Gray100
import woowacourse.payments.ui.theme.Gray200

@Composable
fun StringTextField(
    modifier: Modifier = Modifier,
    label: Int = 0,
    placeholder: Int = 0,
    maxLength: Int = 30,
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val newText = input.text.take(maxLength)
            value = TextFieldValue(
                text = newText,
                selection = TextRange(newText.length)
            )
        },
        label = { Text(stringResource(label), color = Gray200) },
        placeholder = { Text(stringResource(placeholder), color = Gray100) },
        supportingText = {
            Text(
                text = "${value.text.length} / $maxLength",
                color = Gray200,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        modifier = modifier.then(
            Modifier.padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
        ),
    )
}
