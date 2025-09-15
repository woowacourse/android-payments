package woowacourse.payments.ui.common.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.IssuingBank
import woowacourse.payments.ui.theme.cardTextStyle

@Composable
fun IssuingBankName(
    issuingBank: IssuingBank,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = cardTextStyle,
) {
    Text(
        text = issuingBank.getNameResId()?.let { id -> stringResource(id) } ?: "",
        modifier = modifier,
        maxLines = 1,
        style = textStyle,
    )
}

@Preview
@Composable
private fun IssuingBankNamePreview() {
    IssuingBankName(
        issuingBank = IssuingBank.KAKAO,
    )
}
