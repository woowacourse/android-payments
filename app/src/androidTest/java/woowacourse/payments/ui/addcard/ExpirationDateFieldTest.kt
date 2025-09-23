package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.ui.model.CardExpirationDateUiModel

class ExpirationDateFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupExpirationDateField(
        expirationDate: CardExpirationDateUiModel = CardExpirationDate.fromRawInput("").toUiModel(),
        onValueChange: (String) -> Unit = {},
        isValid: Boolean = true,
    ) {
        composeTestRule.setContent {
            ExpirationDateField(
                expirationDate = expirationDate,
                onValueChange = onValueChange,
                isValid = isValid,
            )
        }
    }

    @Test
    fun `onValueChange가_올바른_값으로_호출된다`() {
        // given
        var changedValue = ""
        setupExpirationDateField(onValueChange = { changedValue = it })
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
        setupExpirationDateField(isValid = false)

        // then
        composeTestRule.onNodeWithText("유효한 날짜가 아닙니다.").assertIsDisplayed()
    }
}
