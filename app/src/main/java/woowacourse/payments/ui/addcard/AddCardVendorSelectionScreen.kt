package woowacourse.payments.ui.addcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.model.CardVendors
import woowacourse.payments.ui.addcard.component.VendorItem
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.VendorUiModel
import woowacourse.payments.ui.uimodel.toUiModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCardVendorSelectionScreen(vendors: List<VendorUiModel>) {
    ModalBottomSheet(
        onDismissRequest = {
        },
    ) {
        AddCardVendorSelectionContent(
            vendors = vendors,
            modifier =
                Modifier
                    .padding(bottom = 70.dp)
                    .padding(horizontal = 16.dp),
        )
    }
}

@Composable
private fun AddCardVendorSelectionContent(
    vendors: List<VendorUiModel>,
    modifier: Modifier = Modifier,
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
            VendorItem(vendor)
        }
    }
}

@Preview
@Composable
private fun AddCardVendorSelectionContentPreview() {
    AndroidpaymentsTheme {
        AddCardVendorSelectionContent(
            vendors = CardVendors.entries.map { it.toUiModel() },
        )
    }
}

@Preview
@Composable
private fun AddCardVendorSelectionScreenPreview() {
    AndroidpaymentsTheme {
        AddCardVendorSelectionScreen(
            vendors = CardVendors.entries.map { it.toUiModel() },
        )
    }
}
