package woowacourse.payments.ui.addcard.component

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
import woowacourse.payments.ui.addcard.model.VendorModalUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.VendorUiModel
import woowacourse.payments.ui.uimodel.toUiModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCardVendorModal(
    vendorModalUiState: VendorModalUiState,
    onDismissRequest: () -> Unit = {},
    onVendorItemClick: (VendorUiModel) -> Unit = {},
) {
    if (vendorModalUiState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
        ) {
            AddCardVendorModalContent(
                vendors = vendorModalUiState.vendors,
                modifier =
                    Modifier
                        .padding(bottom = 70.dp)
                        .padding(horizontal = 16.dp),
                onVendorItemClick = onVendorItemClick,
            )
        }
    }
}

@Composable
private fun AddCardVendorModalContent(
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
private fun AddCardVendorModalContentPreview() {
    AndroidpaymentsTheme {
        AddCardVendorModalContent(
            vendors = CardVendor.entries.map { it.toUiModel() },
        )
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddCardVendorModalScreenPreview() {
    AndroidpaymentsTheme {
        AddCardVendorModal(
            vendorModalUiState = VendorModalUiState(),
        )
    }
}
