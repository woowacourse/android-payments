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
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.component.CardCompanyIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    modalBottomSheetState: SheetState,
    onCardCompanySelect: (CardCompany) -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { onFinish() },
        modifier = modifier,
    ) {
        Column {
            Spacer(modifier = Modifier.height(71.dp))

            BankSelectRow(
                onClick = { company -> onCardCompanySelect(company) },
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
    onClick: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        CardCompany.entries.forEach { company ->
            CardCompanyIcon(
                company = company,
                onClick = onClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectRowPreview() {
    BankSelectRow(onClick = {})
}
