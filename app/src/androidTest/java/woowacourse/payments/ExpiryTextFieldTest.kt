package woowacourse.payments

import android.content.Context
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
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.add.components.ExpiryTextField

class ExpiryTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            var expiry by remember { mutableStateOf("") }

            ExpiryTextField(
                value = expiry,
                onValueChange = { expiry = it },
                modifier =
                    Modifier
                        .fillMaxWidth(0.6f)
                        .testTag("expiry"),
            )
        }
    }

    @Test
    fun 만료일은_숫자만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("expiry", useUnmergedTree = true)
            .performTextInput("05abcd")

        // then
        composeRule
            .onNodeWithTag("expiry", useUnmergedTree = true)
            .assertTextEquals("05", includeEditableText = true)
    }

    @Test
    fun 만료일은_구분자로_출력된다() {
        // when
        composeRule
            .onNodeWithTag("expiry", useUnmergedTree = true)
            .performTextInput("051")

        val sep = getSep()
        val expected = listOf("05", "1").joinToString(sep)

        // then
        composeRule
            .onNodeWithTag("expiry", useUnmergedTree = true)
            .assertTextEquals(expected, includeEditableText = true)
    }

    @Test
    fun 만료일은_4자까지만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("expiry", useUnmergedTree = true)
            .performTextInput("05119876")

        val sep = getSep()
        val expected = listOf("05", "11").joinToString(sep)

        // then
        composeRule
            .onNodeWithTag("expiry", useUnmergedTree = true)
            .assertTextEquals(expected, includeEditableText = true)
    }

    private fun getSep(): String {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return context.getString(R.string.expiry_separator)
    }
}
