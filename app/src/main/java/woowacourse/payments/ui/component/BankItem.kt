package woowacourse.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.mapper.toUiModel

@Composable
fun BankItem(
    bank: BankType,
    onClick: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bankUiModel = bank.toUiModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .padding(8.dp)
                .clickable { onClick(bank) },
    ) {
        Image(
            painter = painterResource(bankUiModel.logoRes),
            contentDescription = stringResource(bankUiModel.nameRes),
            modifier = Modifier.size(36.dp),
        )

        Spacer(Modifier.height(4.dp))

        Text(text = stringResource(bankUiModel.nameRes))
    }
}

@Preview(showBackground = true)
@Composable
private fun BankItemPreview() {
    BankItem(
        bank = BankType.KAKAO,
        onClick = {},
    )
}
