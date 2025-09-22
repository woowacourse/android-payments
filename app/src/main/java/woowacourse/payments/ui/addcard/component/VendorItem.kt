package woowacourse.payments.ui.addcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import woowacourse.payments.model.CardVendor
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.VendorUiModel
import woowacourse.payments.ui.uimodel.toUiModel

@Composable
fun VendorItem(
    vendor: VendorUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier =
                Modifier
                    .size(36.dp)
                    .padding(bottom = 8.dp),
            painter = painterResource(vendor.vendorImageId),
            contentDescription = stringResource(R.string.payments_vendor_logo_description),
        )
        Text(
            text = stringResource(vendor.vendorNameId),
            color = colorResource(R.color.payments_vendor_text_color),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview
@Composable
private fun VendorItemPreview() {
    AndroidpaymentsTheme {
        VendorItem(
            CardVendor.BCCard.toUiModel(),
        )
    }
}
