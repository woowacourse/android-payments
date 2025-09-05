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
    fun `텍스트를_입력하면_입력창에_입력한_텍스트가_보인다`() {
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
    fun `카드_소유자_입력창에_입력이_없으면_placeholder를_보여준다`() {
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
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }
}
