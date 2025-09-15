package woowacourse.payments.ui.addcard.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyBottomSheet(
    companies: List<CardCompanyUiModel>,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {},
    ) {
        FlowRow(
            maxItemsInEachRow = 4,
            horizontalArrangement = Arrangement.spacedBy(23.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(23.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 36.dp),
        ) {
            companies.forEach { company: CardCompanyUiModel ->
                CardCompanyButton(company)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CardCompanyBottomSheetPreview() {
    CardCompanyBottomSheet(
        CardCompany.entries.map(CardCompany::toUiModel),
    )
}
