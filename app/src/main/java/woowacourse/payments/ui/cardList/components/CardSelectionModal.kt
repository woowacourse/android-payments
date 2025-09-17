package woowacourse.payments.ui.cardList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.common.model.CardCompany
import woowacourse.payments.ui.theme.Typography

private const val MAX_ITEM_EACH_ROW_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSelectionModal(
    modalBottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    onCardCompanyClick: (cardCompany: CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
    ) {
        CardCompanyGrid(
            companies = CardCompany.entries.drop(1),
            modifier = modifier.fillMaxWidth(),
            onClickCardCompany = onCardCompanyClick,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CardCompanyGrid(
    companies: List<CardCompany>,
    onClickCardCompany: (cardCompany: CardCompany) -> Unit,
    modifier: Modifier = Modifier,
    maxItemsInEachRow: Int = MAX_ITEM_EACH_ROW_COUNT,
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(top = 56.dp, bottom = 82.dp)
                .padding(horizontal = 44.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = maxItemsInEachRow,
    ) {
        companies.forEach { company ->
            CardCompanyItem(company = company, onClickCardCompany = onClickCardCompany)
        }
    }
}

@Composable
private fun CardCompanyItem(
    company: CardCompany,
    onClickCardCompany: (cardCompany: CardCompany) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            Modifier
                .fillMaxWidth(1f / MAX_ITEM_EACH_ROW_COUNT)
                .clickable { onClickCardCompany(company) },
    ) {
        Icon(
            painter = painterResource(company.icon ?: return),
            contentDescription = company.title,
            tint = Color.Unspecified,
            modifier = Modifier.size(37.dp),
        )
        Text(
            text = company.title,
            style = Typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardSelectionModalPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CardCompanyGrid(
            companies = CardCompany.entries,
            modifier = Modifier.fillMaxWidth(),
            onClickCardCompany = { },
        )
    }
}
