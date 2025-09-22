package woowacourse.payments.ui.card.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.BankType

private const val COLUMN_COUNT = 4

@Composable
fun BankSelectRow(
    banks: List<Bank> = emptyList(),
    onBankSelected: (Bank) -> Unit,
) {
    FlowRow(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        banks.forEach { bank ->
            BankItem(
                bank = bank,
                onClick = onBankSelected,
            )
        }
    }
}

@Preview
@Composable
fun BankSelectRowPreview() {
    val banks =
        listOf(
            Bank(BankType.KAKAO, "카카오뱅크"),
            Bank(BankType.KB, "국민은행"),
            Bank(BankType.SHINHAN, "신한은행"),
            Bank(BankType.WOORI, "우리카드"),
            Bank(BankType.KAKAO, "카카오뱅크"),
            Bank(BankType.KB, "국민은행"),
            Bank(BankType.SHINHAN, "신한은행"),
            Bank(BankType.WOORI, "우리카드"),
        )
    BankSelectRow(banks = banks, onBankSelected = {})
}
