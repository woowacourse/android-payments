package woowacourse.payments.ui.features.addcard

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onNavigateBack = { },
                    onNavigateSave = { },
                )
            }
        }
    }

    @Test
    fun 카드_추가_화면의_모든_입력_필드가_표시된다() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val numberLabel = context.getString(R.string.add_card_number_field_title)
        val expiryLabel = context.getString(R.string.add_card_expire_date_field_title)
        val ownerLabel = context.getString(R.string.add_card_owner_name_field_title)
        val passwordLabel = context.getString(R.string.add_card_password_field_title)

        composeTestRule.onNodeWithText(numberLabel).assertExists()
        composeTestRule.onNodeWithText(expiryLabel).assertExists()
        composeTestRule.onNodeWithText(ownerLabel).assertExists()
        composeTestRule.onNodeWithText(passwordLabel).assertExists()
    }
}
