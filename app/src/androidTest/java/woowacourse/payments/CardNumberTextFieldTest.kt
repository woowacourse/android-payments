package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.CardNumberTextField

class CardNumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            var cardNumber by remember { mutableStateOf("") }

            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("card_number"),
            )
        }
    }

    @Test
    fun 카드번호는_숫자만_입력이_가능하다() {
        composeRule
            .onNodeWithTag("card_number", useUnmergedTree = true)
            .performTextInput("2001ab09")

        val sep = getSep()
        val expected = listOf("2001", "09").joinToString(sep)

        composeRule
            .onNodeWithTag("card_number", useUnmergedTree = true)
            .assertTextEquals(expected, includeEditableText = true)
    }

    @Test
    fun 카드번호는_대시로_구분되어_출력된다() {
        composeRule
            .onNodeWithTag("card_number", useUnmergedTree = true)
            .performTextInput("20010928")

        val sep = getSep()
        val expected = listOf("2001", "0928").joinToString(sep)

        composeRule
            .onNodeWithTag("card_number", useUnmergedTree = true)
            .assertTextEquals(expected, includeEditableText = true)
    }

    @Test
    fun 카드번호는_16자리까지만_입력이_가능하다() {
        composeRule
            .onNodeWithTag("card_number", useUnmergedTree = true)
            .performTextInput("200109281999051112345678")

        val sep = getSep()
        val expected = listOf("2001", "0928", "1999", "0511").joinToString(sep)

        composeRule
            .onNodeWithTag("card_number", useUnmergedTree = true)
            .assertTextEquals(expected, includeEditableText = true)
    }

    private fun getSep(): String {
        val context =
            androidx.test.core.app.ApplicationProvider
                .getApplicationContext<android.content.Context>()
        return context.getString(R.string.card_number_separator)
    }
}
