package woowacourse.payments.ui.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CardsTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun isVisibleRegistrationButton이_true면_추가_버튼이_활성화된다() {
        // given && when
        val isVisibleRegistrationButton = true
        composeTestRule.setContent {
            CardsTopAppBar(
                onRegistrationClick = {},
                isVisibleRegistrationButton = isVisibleRegistrationButton,
            )
        }

        // then
        composeTestRule
            .onNode(hasContentDescription("카드 목록 앱 바 추가 버튼"))
            .assertIsDisplayed()
    }

    @Test
    fun isVisibleRegistrationButton이_false면_추가_버튼이_비활성화된다() {
        // given && when
        val isVisibleRegistrationButton = false
        composeTestRule.setContent {
            CardsTopAppBar(
                onRegistrationClick = {},
                isVisibleRegistrationButton = isVisibleRegistrationButton,
            )
        }

        // then
        composeTestRule
            .onNode(hasContentDescription("카드 목록 앱 바 추가 버튼"))
            .assertIsNotDisplayed()
    }
}
