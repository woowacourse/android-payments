package woowacourse.payments.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.PasswordInputField

class PasswordInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var password by remember { mutableStateOf("") }

            PasswordInputField(
                password = password,
                onPasswordChange = { password = it },
            )
        }
    }

    @Test
    fun 초기_화면에_비밀번호_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithContentDescription("비밀번호 Input Field")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // when
        composeTestRule
            .onNodeWithContentDescription("비밀번호 Input Field")
            .performClick()

        // then
        composeTestRule
            .onNodeWithContentDescription("비밀번호 Input Field")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("0000")
            .assertIsDisplayed()
    }
}
