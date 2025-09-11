package woowacourse.payments.ui.component

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
    fun 추가_버튼을_활성화할_수_있다() {
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
    fun 추가_버튼을_비활성화할_수_있다() {
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
