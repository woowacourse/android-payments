package woowacourse.payments.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(start = 12.dp)
            .background(color = Color.White)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
@Preview
fun TextFieldLabelPreview() {
    TextFieldLabel("카드 번호")
}
