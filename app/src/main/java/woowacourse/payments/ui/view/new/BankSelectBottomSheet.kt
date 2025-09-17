package woowacourse.payments.ui.view.new

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Banks
import woowacourse.payments.ui.component.CardCompanyIcon
import woowacourse.payments.ui.core.CompanyResourceProvider
import woowacourse.payments.ui.state.BankState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    resourceProvider: CompanyResourceProvider,
    onBankSelect: (Banks) -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )

    var selectedBank by remember { mutableStateOf<Banks?>(null) }

    LaunchedEffect(key1 = selectedBank) {
        selectedBank?.let { modalBottomSheetState.hide() }
    }

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { onFinish() },
        modifier = modifier,
    ) {
        Column {
            Spacer(modifier = Modifier.height(71.dp))

            BankSelectRow(
                resourceProvider = resourceProvider,
                onClick = { bankType ->
                    selectedBank = bankType
                    onBankSelect(bankType)
                },
                modifier =
                    Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(106.dp))
        }
    }
}

private const val COLUMN_COUNT = 4

@Composable
fun BankSelectRow(
    resourceProvider: CompanyResourceProvider,
    onClick: (Banks) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        Banks.entries.forEachIndexed { index, bankType ->
            resourceProvider.getCompanyName(BankState.Bank(bankType))?.let { bankName ->
                CardCompanyIcon(
                    bankIcon = resourceProvider.getCompanyIcon(bankType),
                    bankName = stringResource(bankName),
                    banks = bankType,
                    onClick = onClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectRowPreview() {
    BankSelectRow(
        resourceProvider = CompanyResourceProvider(),
        onClick = {},
    )
}
