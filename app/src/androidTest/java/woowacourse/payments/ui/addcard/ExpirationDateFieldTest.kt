package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.ui.model.CardExpirationDateUiModel

class ExpirationDateFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var changedValue = ""

    @Before
    fun setup() {
        changedValue = ""
        composeTestRule.setContent {
            ExpirationDateField(
                expirationDate = CardExpirationDate.fromRawInput("").toUiModel(),
                onValueChange = { changedValue = it },
                isValid = true,
            )
        }
    }

    @Test
    fun `onValueChange가_올바른_값으로_호출된다`() {
        // given
        val inputField = composeTestRule.onNodeWithText("만료일")

        // when
        val expirationDateInput = "1234"
        inputField.performTextInput(expirationDateInput)

        // then
        assert(changedValue == "1234")
    }

    @Test
    fun `isValid가_false이면_오류_메시지가_표시된다`() {
        // given + when
        composeTestRule.setContent { // Override setup for this specific test
            ExpirationDateField(
                expirationDate = CardExpirationDate.fromRawInput("").toUiModel(),
                onValueChange = { changedValue = it },
                isValid = false,
            )
        }

        // then
        composeTestRule.onNodeWithText("유효한 날짜가 아닙니다.").assertIsDisplayed()
    }
}
