package woowacourse.payments.ui.addcard.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import woowacourse.payments.ui.addcard.SubmitCardScreenUiStateHolder
import woowacourse.payments.ui.model.CardCompanyUiModel

private const val MAX_ITEMS_PER_ROW = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyBottomSheet(
    cardCompanies: List<CardCompanyUiModel>,
    onCompanySelected: (CardCompanyUiModel) -> Unit,
    onDismissed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState: SheetState = rememberModalBottomSheetState { false }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        sheetState.expand()
    }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissed,
    ) {
        FlowRow(
            maxItemsInEachRow = MAX_ITEMS_PER_ROW,
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp),
        ) {
            cardCompanies.forEach { company: CardCompanyUiModel ->
                CardCompanyButton(
                    company,
                    {
                        onCompanySelected(company)
                        coroutineScope.launch { sheetState.hide() }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardCompanyBottomSheetPreview() {
    CardCompanyBottomSheet(SubmitCardScreenUiStateHolder.CARD_COMPANIES, {}, {})
}
