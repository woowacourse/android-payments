package woowacourse.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.StringTextField

class OwnerTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            var owner by remember { mutableStateOf("") }
            StringTextField(
                modifier = Modifier.testTag("owner"),
                value = owner,
                onValueChange = { owner = it },
                maxLength = 30,
            )
        }
    }

    @Test
    fun 카드소유자_이름은_30자까지만_입력이_가능하다() {
        val long = "Thisisaverylongsentencewithoutanyspacesanditexceedsthirtycharacters"
        val expect = long.take(30)

        // when
        composeRule.onNodeWithTag("owner").performTextInput(long)

        // then
        composeRule.onNodeWithText(expect).assertIsDisplayed()
    }
}
