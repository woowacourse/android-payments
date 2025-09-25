@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.ui.registration.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.model.CardCompanyUiModel

@Composable
fun CardCompanySelectBottomSheet(
    onCardCompanyClick: (CardCompanyUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        CardCompanySelectRow(
            onCardCompanyClick = onCardCompanyClick,
        )
    }
}
