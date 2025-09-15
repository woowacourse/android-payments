package woowacourse.payments.ui.features.addcard.components.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCardCompanyItem(
    modifier: Modifier = Modifier,
    value: CardCompanyUiModel,
) {
    Column(
        modifier =
            modifier
                .width(78.dp)
                .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (value.iconResId != null) {
            val companyName = stringResource(value.companyNameResId)
            Image(
                painter = painterResource(value.iconResId),
                contentDescription =
                    stringResource(
                        R.string.add_card_bottom_sheet_card_company_icon_description,
                        companyName,
                    ),
                modifier = Modifier.size(width = 37.dp, height = 37.dp),
            )
        }
        Spacer(modifier = Modifier.size(9.dp))
        Text(
            text = stringResource(value.companyNameResId),
            fontSize = 16.sp,
            letterSpacing = (-0.085).em,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetCardCompanyItemBCPreview() {
    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetCardCompanyItem(
            value = CardCompanyUiModel.BC,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetCardCompanyItemHyundaiPreview() {
    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetCardCompanyItem(
            value = CardCompanyUiModel.HYUNDAI,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetCardCompanyItemKakaoPreview() {
    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetCardCompanyItem(
            value = CardCompanyUiModel.KAKAO,
        )
    }
}
