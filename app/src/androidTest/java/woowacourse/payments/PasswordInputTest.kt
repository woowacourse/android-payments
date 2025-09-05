package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.component.PasswordInputField

class PasswordInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PasswordInputField(
                password = Password(""),
                onPasswordChange = { },
            )
        }
    }

    @Test
    fun 초기_화면에_비밀번호_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // given

        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("0000")
            .assertIsDisplayed()
    }
}
