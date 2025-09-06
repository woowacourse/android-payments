package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Expired
import woowacourse.payments.ui.component.ExpiredInputField

class ExpiredInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 초기_화면에_만료일_텍스트가_표시된다() {
        // given
        composeTestRule.setContent {
            ExpiredInputField(
                expired = null,
                onExpiredChange = { },
            )
        }

        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // given
        composeTestRule.setContent {
            ExpiredInputField(
                expired = null,
                onExpiredChange = { },
            )
        }

        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("MM / YY")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일을_입력하면_2글자_기준으로_기호가_삽입된다() {
        // given
        composeTestRule.setContent {
            ExpiredInputField(
                expired = Expired("1029"),
                onExpiredChange = { },
            )
        }

        // then
        composeTestRule
            .onNodeWithText("10 / 29")
            .assertIsDisplayed()
    }
}
