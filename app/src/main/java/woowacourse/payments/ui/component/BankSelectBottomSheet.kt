package woowacourse.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2
val bankList =
    listOf(
        BankType.BC,
        BankType.SHINHAN,
        BankType.KAKAOBANK,
        BankType.HYUNDAI,
        BankType.WOORI,
        BankType.LOTTE,
        BankType.HANA,
        BankType.KB,
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    selectedBank: BankType = NOT_SELECTED,
    onBankSelected: (BankType) -> Unit,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )
    var selectedBank by remember { mutableStateOf(selectedBank) }

    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != NOT_SELECTED) {
            modalBottomSheetState.hide()
            onBankSelected(selectedBank)
        }
    }

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
            BankSelectRow(
                bankList = bankList,
                onBankSelected = { bank ->
                    selectedBank = bank
                },
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BankSelectRow(
    bankList: List<BankType>,
    onBankSelected: (BankType) -> Unit,
) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        bankList.take(ROW_COUNT * COLUMN_COUNT).forEach { bank ->
            BankSelectItem(
                bankType = bank,
                onClick = { onBankSelected(bank) },
            )
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
        val imageRes =
            when (bankType) {
                BankType.BC -> R.drawable.img_bc
                BankType.SHINHAN -> R.drawable.img_shinhan
                BankType.KAKAOBANK -> R.drawable.img_kakaobank
                BankType.HYUNDAI -> R.drawable.img_hyundai
                BankType.WOORI -> R.drawable.img_woori
                BankType.LOTTE -> R.drawable.img_lotte
                BankType.HANA -> R.drawable.img_hana
                BankType.KB -> R.drawable.img_kb
                else -> R.drawable.ic_not_visible
            }

        Image(
            painter = painterResource(id = imageRes),
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
fun BankSelectRowPreview() {
    BankSelectRow(
        bankList = BankType.entries,
        onBankSelected = {},
    )
}
