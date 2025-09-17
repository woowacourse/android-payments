@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.ui.component.registration

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.CardCompanyUiModel

@Composable
fun CardCompanySelectBottomSheet(
    onCardCompanyClick: (CardCompanyUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier,
    ) {
        CardCompanySelectRow(
            onCardCompanyClick = onCardCompanyClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanySelectBottomSheetPreview() {
    CardCompanySelectBottomSheet(
        onCardCompanyClick = {},
        onDismissRequest = {},
    )
}
