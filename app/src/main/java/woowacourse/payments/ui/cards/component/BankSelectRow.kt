package woowacourse.payments.ui.cards.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.BankViewType

private const val COLUMN_COUNT: Int = 4

@Composable
fun BankSelectRow(
    onBankClick: (BankViewType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        BankViewType.entries.forEach { bankViewType ->
            if (bankViewType != BankViewType.NONE) {
                BankSelectionButton(
                    bankViewType = bankViewType,
                    onClick = { onBankClick(bankViewType) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun BankSelectionButton(
    bankViewType: BankViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (bankViewType.imageRes != null && bankViewType.nameRes != null) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(bankViewType.imageRes),
                contentDescription = bankViewType.name,
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(text = stringResource(bankViewType.nameRes), fontSize = 16.sp, lineHeight = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectCardButtonPreview() {
    BankSelectionButton(
        bankViewType = BankViewType.BC,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun SelectCardButtonsPreview() {
    BankSelectRow(onBankClick = {})
}
