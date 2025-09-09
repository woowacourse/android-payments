package woowacourse.payments.cardaddition.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardOwnerNameTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            val (ownerName: String, setOwnerName: (String) -> Unit) = remember { mutableStateOf("") }

            CardOwnerNameTextField(
                value = ownerName,
                onValueChange = setOwnerName,
            )
        }
    }

    @Test
    fun `입력된_이름의_길이가_출력된다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("모찌")

        // then
        composeRule
            .onNodeWithText("2/30")
            .assertIsDisplayed()
    }
}
