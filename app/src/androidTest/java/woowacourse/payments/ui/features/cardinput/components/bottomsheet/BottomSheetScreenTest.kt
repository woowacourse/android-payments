package woowacourse.payments.ui.features.cardinput.components.bottomsheet

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
class BottomSheetScreenTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun UNKNOWN_카드를_제외한_모든_카드_아이템이_표시된다() {
        // given

        compose.setContent {
            val sheetState =
                rememberStandardBottomSheetState(
                    initialValue = SheetValue.Expanded,
                    confirmValueChange = { false },
                )

            AndroidpaymentsTheme(dynamicColor = false) {
                BottomSheetScreen(
                    sheetState = sheetState,
                    onDismiss = {},
                    onItemClick = {},
                )
            }
        }

        // then
        CardCompanyUiModel.entries.filter { it != CardCompanyUiModel.UNKNOWN }.forEach {
            compose
                .onNodeWithText(compose.activity.getString(it.companyNameResId))
                .assertIsDisplayed()
        }
    }
}
