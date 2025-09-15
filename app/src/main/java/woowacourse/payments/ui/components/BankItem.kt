package woowacourse.payments.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey52

@Composable
fun BankItem(
    bank: BankUiModel,
    onClick: (BankUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable { onClick(bank) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(bank.image),
            contentDescription = stringResource(bank.label),
            modifier = Modifier.size(37.dp),
            contentScale = ContentScale.Fit,
        )
        Spacer(Modifier.height(9.dp))
        Text(
            text = stringResource(bank.label),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Grey52,
        )
    }
}

@Preview(name = "BankItem", showBackground = true)
@Composable
private fun BankItemPreview() {
    AndroidpaymentsTheme {
        BankItem(
            bank =
                BankUiModel(
                    type = BankType.SHINHAN,
                    image = R.drawable.ic_bank_shinhan,
                    label = R.string.bank_shinhan,
                    background = Color(0xFF3946FF),
                ),
            onClick = {},
        )
    }
}
