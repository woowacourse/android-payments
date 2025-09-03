package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CardOwnerTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `입력한_텍스트가_입력창에_보인다`() {
        // given
        val ownerName = "페토"

        // when
        composeTestRule.setContent {
            CardOwnerTextField(
                ownerName = ownerName,
                maxLength = 10,
                onChangeOwnerName = {}
            )
        }

        // then
        composeTestRule.onNodeWithText(ownerName).assertIsDisplayed()
    }

    @Test
    fun `placeHolder가_보인다`() {
        // given
        composeTestRule.setContent {
            CardOwnerTextField(
                ownerName = "",
                maxLength = 10,
                onChangeOwnerName = {}
            )
        }

        // then
        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }
}
