package woowacourse.payments.ui.addcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.model.CardVendor
import woowacourse.payments.ui.addcard.component.VendorItem
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.VendorUiModel
import woowacourse.payments.ui.uimodel.toUiModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCardVendorSelectionScreen(
    vendors: List<VendorUiModel>,
    onDismissRequest: () -> Unit = {},
    onVendorItemClick: (VendorUiModel) -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
    ) {
        AddCardVendorSelectionContent(
            vendors = vendors,
            modifier =
                Modifier
                    .padding(bottom = 70.dp)
                    .padding(horizontal = 16.dp),
            onVendorItemClick = onVendorItemClick,
        )
    }
}

@Composable
private fun AddCardVendorSelectionContent(
    vendors: List<VendorUiModel>,
    modifier: Modifier = Modifier,
    onVendorItemClick: (VendorUiModel) -> Unit = {},
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = 4,
    ) {
        vendors.forEach { vendor ->
            VendorItem(
                vendor = vendor,
                modifier =
                    Modifier.clickable { onVendorItemClick(vendor) },
            )
        }
    }
}

@Preview
@Composable
private fun AddCardVendorSelectionContentPreview() {
    AndroidpaymentsTheme {
        AddCardVendorSelectionContent(
            vendors = CardVendor.entries.map { it.toUiModel() },
        )
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddCardVendorSelectionScreenPreview() {
    AndroidpaymentsTheme {
        AddCardVendorSelectionScreen(
            vendors = CardVendor.entries.map { it.toUiModel() },
        )
    }
}
