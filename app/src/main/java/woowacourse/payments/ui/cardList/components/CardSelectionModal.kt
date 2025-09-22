package woowacourse.payments.ui.cardList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import woowacourse.payments.domain.model.CardCompany
import woowacourse.payments.ui.common.model.CardCompanyUiType
import woowacourse.payments.ui.common.model.toUiType
import woowacourse.payments.ui.theme.Typography

private const val MAX_ITEM_EACH_ROW_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSelectionModal(
    modalBottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    onCardCompanyClick: (cardCompany: CardCompanyUiType) -> Unit,
    cardCompanies: List<CardCompanyUiType>,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        CardCompanyGrid(
            companies = cardCompanies,
            modifier = modifier.fillMaxWidth(),
            onClickCardCompany = onCardCompanyClick,
        )
    }
}

@Composable
private fun CardCompanyGrid(
    companies: List<CardCompanyUiType>,
    onClickCardCompany: (cardCompany: CardCompanyUiType) -> Unit,
    modifier: Modifier = Modifier,
    maxItemsInEachRow: Int = MAX_ITEM_EACH_ROW_COUNT,
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(top = 36.dp, bottom = 112.dp)
                .padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = maxItemsInEachRow,
    ) {
        companies.forEach { company ->
            CardCompanyItem(
                company = company,
                onClickCardCompany = onClickCardCompany,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun CardCompanyItem(
    company: CardCompanyUiType,
    onClickCardCompany: (cardCompany: CardCompanyUiType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .fillMaxWidth(1f / MAX_ITEM_EACH_ROW_COUNT)
                .clickable { onClickCardCompany(company) },
    ) {
        Icon(
            painter = painterResource(company.icon ?: return),
            contentDescription = company.title,
            tint = Color.Unspecified,
            modifier = Modifier.size(37.dp),
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = company.title.orEmpty(),
            color = Color.Unspecified,
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
            companies = CardCompany.entries.drop(1).map { it.toUiType() },
            modifier = Modifier.fillMaxWidth(),
            onClickCardCompany = { },
        )
    }
}
