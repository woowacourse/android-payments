package woowacourse.payments.component

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.InputType
import woowacourse.payments.format
import woowacourse.payments.ui.theme.Gray100
import woowacourse.payments.ui.theme.Gray200

@Preview(showBackground = true)
@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    label: Int = 0,
    placeholder: Int = 0,
    inputType: InputType = InputType.Normal,
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val formatted = inputType.format(input.text)
            value = TextFieldValue(
                text = formatted,
                selection = TextRange(formatted.length)
            )
        },
        label = { Text(stringResource(label), color = Gray200) },
        placeholder = { Text(stringResource(placeholder), color = Gray100) },
        visualTransformation = if (inputType is InputType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier.then(
            Modifier.padding(start = 24.dp, end = 24.dp, bottom = 30.dp)
        ),
    )
}
