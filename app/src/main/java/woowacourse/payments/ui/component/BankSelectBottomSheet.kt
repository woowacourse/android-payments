package woowacourse.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.BankType.NOT_SELECTED
import woowacourse.payments.ui.util.toCardCompanyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBankSelected: (BankType) -> Unit,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(confirmValueChange = { false })

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { onDismiss() },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 48.dp),
        ) {
            FlowRow(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                BankType.entries.filter { it != NOT_SELECTED }.forEach { bank ->
                    BankSelectItem(
                        bankType = bank,
                        onClick = { onBankSelected(bank) },
                    )
                }
            }
        }
    }
}

@Composable
fun BankSelectItem(
    bankType: BankType,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .width(80.dp)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Image(
            painter = painterResource(id = bankType.toCardCompanyUiModel().logoRes),
            contentDescription = stringResource(R.string.bank_item_content_description),
            modifier = Modifier.size(48.dp),
        )

        Text(
            text = bankType.toCardCompanyUiModel().name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectItemPreview() {
    BankSelectItem(
        bankType = BankType.KB,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(
        onBankSelected = {},
        onDismiss = { },
    )
}
