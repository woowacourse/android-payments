package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class TextFieldLabelTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 라벨_텍스트가_정상적으로_표시된다() {
        // given
        val label = "카드 번호"

        // when
        composeTestRule.setContent {
            TextFieldLabel(text = label)
        }

        // then
        composeTestRule.onNodeWithText(label)
            .assertIsDisplayed()
    }
}
