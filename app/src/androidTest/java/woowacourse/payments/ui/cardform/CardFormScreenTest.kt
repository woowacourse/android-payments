package woowacourse.payments.ui.cardform

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardFormScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CardFormScreen(
                onBackPressed = {},
                onCardRegistered = {},
            )
        }
    }

    @Test
    fun `카드_번호와_만료일_그리고_비밀번호_카드사_선택_모두_유효한_경우_완료_버튼이_활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1299"
        val cardPassword = "1234"

        // when
        composeTestRule.run {
            onNodeWithContentDescription("카드 번호").performTextInput(cardNumber)
            onNodeWithContentDescription("만료일").performTextInput(cardExpirationDate)
            onNodeWithContentDescription("비밀번호").performTextInput(cardPassword)
            onNodeWithContentDescription("카드 정보").performClick()
            onNodeWithText("카카오뱅크").performClick()
        }

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsEnabled()
    }

    @Test
    fun `카드_번호가_유효하지_않은_경우_완료_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234"
        val cardExpirationDate = "1299"
        val cardPassword = "1234"

        // when
        composeTestRule.run {
            onNodeWithContentDescription("카드 번호").performTextInput(cardNumber)
            onNodeWithContentDescription("만료일").performTextInput(cardExpirationDate)
            onNodeWithContentDescription("비밀번호").performTextInput(cardPassword)
        }

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsNotEnabled()
    }

    @Test
    fun `카드_만료일이_유효하지_않은_경우_완료_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1599"
        val cardPassword = "1234"

        // when
        composeTestRule.run {
            onNodeWithContentDescription("카드 번호").performTextInput(cardNumber)
            onNodeWithContentDescription("만료일").performTextInput(cardExpirationDate)
            onNodeWithContentDescription("비밀번호").performTextInput(cardPassword)
        }

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsNotEnabled()
    }

    @Test
    fun `카드_비밀번호가_유효하지_않은_경우_완료_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1299"
        val cardPassword = "1"

        // when
        composeTestRule.run {
            onNodeWithContentDescription("카드 번호").performTextInput(cardNumber)
            onNodeWithContentDescription("만료일").performTextInput(cardExpirationDate)
            onNodeWithContentDescription("비밀번호").performTextInput(cardPassword)
        }

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsNotEnabled()
    }
}
