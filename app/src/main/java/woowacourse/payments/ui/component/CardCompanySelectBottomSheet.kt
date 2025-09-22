package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.CardCompanyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanySelectBottomSheet(
    onCompanyClick: (CardCompanyUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = Color.White,
    ) {
        CardCompanySelectRow(
            modifier = Modifier.navigationBarsPadding(),
            onCompanyClick = { company ->
                onCompanyClick(company)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun CardCompanySelectBottomSheetPreview() {
    CardCompanySelectBottomSheet(
        onCompanyClick = {},
        onDismissRequest = {},
    )
}
