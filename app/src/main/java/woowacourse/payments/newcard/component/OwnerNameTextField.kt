package woowacourse.payments.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import woowacourse.payments.domain.OwnerName

@Composable
fun OwnerNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                onValueChange(value)
            }
        },
        label = { Text("카드 소유자 이름(선택)") },
        placeholder = {
            Text(
                text = "카드에 표시된 이름을 입력하세요.",
                color = Color.Gray,
            )
        },
        singleLine = true,
        supportingText = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "${value.length}/${OwnerName.MAX_LENGTH}",
                )
            }
        },
        modifier = modifier,
    )
}

private fun isValidInput(ownerName: String): Boolean = ownerName.length <= OwnerName.MAX_LENGTH
