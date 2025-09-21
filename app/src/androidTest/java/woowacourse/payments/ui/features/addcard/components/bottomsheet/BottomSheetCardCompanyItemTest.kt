package woowacourse.payments.ui.features.addcard.components.bottomsheet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class BottomSheetCardCompanyItemTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun 카드사_아이템에_아이콘_이미지와_카드사_명이_존재한다() {
        // given
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val kakaoUiModel = CardCompanyUiModel.KAKAO
        val kakaoCompanyName = context.getString(kakaoUiModel.companyNameResId)
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
                context.getString(
                    R.string.add_card_bottom_sheet_card_company_icon_description,
                    kakaoCompanyName,
                ),
            ).assertIsDisplayed()
        compose.onNodeWithText(context.getString(kakaoUiModel.companyNameResId)).assertIsDisplayed()
    }
}
