package woowacourse.payments.ui.payments.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.payments.model.BankUiModel

@Composable
fun BankButton(
    bankUiModel: BankUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = bankUiModel.bankLogo),
            contentDescription = null,
            modifier = Modifier.wrapContentSize(),
        )
        Text(
            text = stringResource(bankUiModel.bankName),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 1.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankTextFieldPreview() {
    BankButton(BankUiModel.KB)
}
