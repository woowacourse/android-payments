package woowacourse.payments.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.theme.GrayAA
import woowacourse.payments.R

@Composable
fun TextFieldPlaceHolder(
    @StringRes textResourceId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        color = GrayAA,
        text = stringResource(textResourceId),
        modifier = modifier
    )
}

@Composable
@Preview
fun TextFieldPlaceHolderPreview() {
    TextFieldPlaceHolder(
        textResourceId = R.string.card_number,
        modifier = Modifier
    )
}
