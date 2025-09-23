package woowacourse.payments.ui.features.cartinput

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardInputScreenTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        compose.setContent {
            AndroidpaymentsTheme {
                CardInputScreen(
                    onNavigateBack = { },
                    onNavigateSave = { },
                )
            }
        }
    }

    @Test
    fun 카드_추가_화면의_모든_입력_필드가_표시된다() {
        val numberLabel = compose.activity.getString(R.string.add_card_number_field_title)
        val expiryLabel = compose.activity.getString(R.string.add_card_expire_date_field_title)
        val ownerLabel = compose.activity.getString(R.string.add_card_owner_name_field_title)
        val passwordLabel = compose.activity.getString(R.string.add_card_password_field_title)

        compose.onNodeWithText(numberLabel).assertExists()
        compose.onNodeWithText(expiryLabel).assertExists()
        compose.onNodeWithText(ownerLabel).assertExists()
        compose.onNodeWithText(passwordLabel).assertExists()
    }
}
