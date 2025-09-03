package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardOwner

@Composable
fun CardOwnerInput(
    cardOwner: CardOwner?,
    onOwnerChange: (CardOwner?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                if (newValue.text.length <= 30) {
                    textFieldValue = newValue
                    onOwnerChange(CardOwner.create(newValue.text))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("카드에 표시된 이름을 입력하세요.", color = Color.LightGray) },
            label = { Text(text = "카드 소유자 이름(선택)") },
            isError = showValidationError && (cardOwner?.isValid != true),
        )
        Text(
            text = "${textFieldValue.text.length} / 30",
            modifier =
                Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp, end = 4.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
