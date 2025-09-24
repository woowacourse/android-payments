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
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.ExpiredInputField

class ExpiredInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var expired by remember { mutableStateOf("") }

            ExpiredInputField(
                expired = expired,
                onExpiredChange = { expired = it },
            )
        }
    }

    @Test
    fun 초기_화면에_만료일_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithContentDescription("만료일 Input Field")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // when
        composeTestRule
            .onNodeWithContentDescription("만료일 Input Field")
            .performClick()

        // then
        composeTestRule
            .onNodeWithContentDescription("만료일 Input Field")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("MM / YY")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일을_입력하면_2글자_기준으로_기호가_삽입된다() {
        // when
        composeTestRule
            .onNodeWithContentDescription("만료일 Input Field")
            .performTextInput("1029")

        // then
        composeTestRule
            .onNodeWithText("10 / 29")
            .assertIsDisplayed()
    }
}
