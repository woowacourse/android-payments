package woowacourse.payments.ui.addcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.BankTypeUiModel

@Composable
fun BankSelectBtn(
    bank: BankTypeUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .width(69.dp)
                .height(65.dp)
                .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BankImage(bank, Modifier.size(37.dp))
        Text(stringResource(id = bank.bankName), fontSize = 16.sp, letterSpacing = (-0.085).em)
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBtnPreview() {
    BankSelectBtn(
        bank = BankTypeUiModel.BC,
        onClick = {},
    )
}
