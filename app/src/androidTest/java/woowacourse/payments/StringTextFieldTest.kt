package woowacourse.payments

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
import woowacourse.payments.component.StringTextField

class StringTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            StringTextField(
                modifier = Modifier.testTag("owner"),
                label = R.string.label_owner,
                placeholder = R.string.placeholder_owner,
                maxLength = 30
            )
        }
    }

    @Test
    fun 카드소유자_이름은_30자까지만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("owner")
            .performTextInput("Thisisaverylongsentencewithoutanyspacesanditexceedsthirtycharacters")

        // then
        composeRule
            .onNodeWithText("Thisisaverylongsentencewithout")
            .assertIsDisplayed()
    }
}