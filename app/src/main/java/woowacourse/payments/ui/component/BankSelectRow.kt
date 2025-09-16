package woowacourse.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.BankTypeUiModel

@Composable
fun BankSelectRow(
    onBankSelected: (BankTypeUiModel) -> Unit,
    modifier: Modifier = Modifier,
    columnCount: Int = 4,
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        maxItemsInEachRow = columnCount,
    ) {
        BankTypeUiModel.entries.forEach { bankType ->
            if (bankType == BankTypeUiModel.NOT_SELECTED) return@forEach
            BankSelectButton(
                bankType = bankType,
                onClick = onBankSelected,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun BankSelectButton(
    bankType: BankTypeUiModel,
    onClick: (BankTypeUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable { onClick(bankType) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        bankType.logoResId?.let { resId ->
            Image(
                painter = painterResource(id = resId),
                contentDescription = bankType.name,
                modifier = Modifier.padding(8.dp),
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        bankType.nameResId?.let { resId ->
            Text(
                text = stringResource(resId),
                fontWeight = W500,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(onBankSelected = {})
}
