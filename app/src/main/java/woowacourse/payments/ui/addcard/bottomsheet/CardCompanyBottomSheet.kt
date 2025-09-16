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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyBottomSheet(
    companies: List<CardCompanyUiModel>,
    onCompanySelected: (CardCompanyUiModel) -> Unit,
    onDismissed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val showBottomSheet: MutableState<Boolean> = remember { mutableStateOf(true) }
    val sheetState: SheetState = rememberModalBottomSheetState { false }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    if (showBottomSheet.value) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = sheetState,
            onDismissRequest = onDismissed,
        ) {
            FlowRow(
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.spacedBy(23.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(23.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 36.dp),
            ) {
                companies.forEach { company: CardCompanyUiModel ->
                    CardCompanyButton(
                        company,
                        {
                            onCompanySelected(company)
                            coroutineScope
                                .launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    showBottomSheet.value = false
                                }
                        },
                    )
                }
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
        {},
        {},
    )
}
