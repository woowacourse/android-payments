package woowacourse.payments.ui.newcard.dialog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.util.extensions.toLabel

@Composable
fun BankItem(
    bank: Bank,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(37.dp),
            painter = painterResource(id = bank.icon),
            contentDescription = stringResource(R.string.new_card_bank_icon_description, stringResource(bank.type.toLabel())),
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(bank.type.toLabel()),
            fontWeight = W500,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = (-0.085).em,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankItemPreview() {
    BankItem(
        bank =
            Bank(
                type = BankType.BC,
                icon = R.drawable.ic_bc,
            ),
    )
}
