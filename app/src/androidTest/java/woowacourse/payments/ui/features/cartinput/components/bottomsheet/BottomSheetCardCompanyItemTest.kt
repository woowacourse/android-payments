package woowacourse.payments.ui.features.cartinput.components.bottomsheet

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class BottomSheetCardCompanyItemTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun 카드사_아이템에_아이콘_이미지와_카드사_명이_존재한다() {
        // given
        val kakaoUiModel = CardCompanyUiModel.KAKAO
        val kakaoCompanyName = compose.activity.getString(kakaoUiModel.companyNameResId)
        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                BottomSheetCardCompanySelectableItem(
                    value = kakaoUiModel,
                )
            }
        }

        // then
        compose
            .onNodeWithContentDescription(
                compose.activity.getString(
                    R.string.add_card_bottom_sheet_card_company_icon_description,
                    kakaoCompanyName,
                ),
            ).assertIsDisplayed()
        compose
            .onNodeWithText(compose.activity.getString(kakaoUiModel.companyNameResId))
            .assertIsDisplayed()
    }
}
