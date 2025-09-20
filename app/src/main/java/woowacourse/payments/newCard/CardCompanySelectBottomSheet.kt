package woowacourse.payments.newCard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanySelectBottomSheet(
    modalBottomSheetState: SheetState,
    onClick: (CardCompanyUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { onDismissRequest() },
        modifier = modifier
            .fillMaxWidth()
    ) {
        CardCompanySelectRow(
            onClick = onClick,
            companies = CardCompany.entries
                .filter { it != CardCompany.NOT_SELECTED }
                .map { it.toUiModel() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 70.dp)
                .height(227.dp),
        )
    }
}
