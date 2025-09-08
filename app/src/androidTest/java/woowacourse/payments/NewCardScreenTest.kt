package woowacourse.payments

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@RunWith(AndroidJUnit4::class)
class NewCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun newCardScreen_카드번호입력_상태가_업데이트된다() {
        // Given
        composeTestRule.setContent {
            AndroidpaymentsTheme {
                NewCardScreen()
            }
        }

        val testCardNumber = "1234567890123456"

        // When
        composeTestRule.onNodeWithText("카드 번호")
            .performTextInput(testCardNumber)

        // Then
        composeTestRule.onNodeWithText(testCardNumber).assertExists()
    }

    @Test
    fun newCardScreen_비밀번호입력_상태가_업데이트된다() {
        // Given
        composeTestRule.setContent {
            AndroidpaymentsTheme {
                NewCardScreen()
            }
        }

        val testPassword = "1234"

        // When
        composeTestRule.onNodeWithText("비밀번호")
            .performTextInput(testPassword)

        // Then - 비밀번호는 마스킹되어 표시되므로 입력 필드가 비어있지 않은지 확인
        composeTestRule.onNodeWithText("비밀번호")
            .assertTextContains("••••") // 실제 구현에 따라 마스킹 문자가 다를 수 있음
    }
}
