@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.ui.registration.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.model.CardCompanyUiModel

@Composable
fun CardCompanySelectBottomSheet(
    sheetState: SheetState,
    onCardCompanyClick: (CardCompanyUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        CardCompanySelectRow(
            onCardCompanyClick = onCardCompanyClick,
        )
    }
}
