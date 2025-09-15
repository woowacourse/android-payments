package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.IssuingBank

private const val COLUMN_COUNT = 4

@Composable
fun BankSelectRow(
    issuingBanks: List<IssuingBank>,
    onBankSelect: (IssuingBank) -> Unit,
    modifier: Modifier = Modifier,
    column: Int = COLUMN_COUNT,
) {
    val context = LocalContext.current
    FlowRow(
        modifier =
            modifier.semantics {
                contentDescription = context.getString(R.string.card_addition_issuing_banks_description)
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = column,
    ) {
        issuingBanks.forEach { issuingBank ->
            BankInfo(
                onBankSelect = onBankSelect,
                modifier =
                    Modifier
                        .size(width = 80.dp, height = 70.dp)
                        .semantics {
                            contentDescription =
                                context.getString(R.string.card_addition_issuing_bank_info_description)
                        },
                issuingBank = issuingBank,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(
        issuingBanks = IssuingBank.entries.filter { bank -> bank != IssuingBank.NOT_SELECTED },
        onBankSelect = {},
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 36.dp),
    )
}
