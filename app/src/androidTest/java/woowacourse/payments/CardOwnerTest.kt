package woowacourse.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.LimitedUppercaseTextField

@Suppress("ktlint:standard:function-naming")
class CardOwnerTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var text by remember { mutableStateOf("") }
            LimitedUppercaseTextField(
                text = text,
                onValueChange = { text = it },
                label = "카드 소유자 이름(선택)",
                hint = "카드에 표시된 이름을 입력하세요.",
                maxLength = 30,
            )
        }
    }

    @Test
    fun 카드_소유자_이름은_30자를_초과할_수_없다() {
        // given
        val input = "a".repeat(31)
        val expected = input.uppercase().take(30)

        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput(input)

        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
