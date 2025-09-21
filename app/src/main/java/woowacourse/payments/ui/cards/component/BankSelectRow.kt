package woowacourse.payments.ui.cards.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.BankViewType

private const val COLUMN_COUNT: Int = 4

@Composable
fun BankSelectRow(
    modifier: Modifier = Modifier,
    onBankClick: (BankViewType) -> Unit,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        BankViewType.entries.forEach { bankViewType ->
            if (bankViewType.nameRes != null && bankViewType.imageRes != null) {
                BankSelectionButton(
                    imageRes = bankViewType.imageRes,
                    nameRes = bankViewType.nameRes,
                    onClick = { onBankClick(bankViewType) },
                    contentDescription = bankViewType.name,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun BankSelectionButton(
    modifier: Modifier = Modifier,
    imageRes: Int,
    nameRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(imageRes),
            contentDescription = contentDescription,
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(text = stringResource(nameRes), fontSize = 16.sp, lineHeight = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectCardButtonPreview() {
    BankSelectionButton(
        imageRes = R.drawable.img_bc,
        nameRes = R.string.bc_card,
        onClick = {},
        contentDescription = "",
    )
}

@Preview(showBackground = true)
@Composable
private fun SelectCardButtonsPreview() {
    BankSelectRow(onBankClick = {})
}
