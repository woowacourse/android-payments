package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardAdditionScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            CardAdditionScreen()
        }
    }

    @Test
    fun `화면_진입_시_카드사_선택창이_출력된다`() {
        // then
        composeRule
            .onNodeWithContentDescription("카드사 선택창")
            .assertIsDisplayed()
    }

    @Test
    fun `외부_영역을_터치해도_카드사_선택창이_사라지지_않는다`() {
        // when
        composeRule
            .onNodeWithContentDescription("카드 정보 입력창")
            .performClick()

        // then
        composeRule
            .onNodeWithContentDescription("카드사 선택창")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사를_선택하고_카드_정보를_입력하면_완료_버튼이_활성화된다`() {
        // when
        composeRule.apply {
            onNodeWithText("카카오뱅크")
                .performClick()

            onNodeWithContentDescription("카드 번호 입력창")
                .performTextInput("1234123412341234")

            onNodeWithContentDescription("카드 만료일 입력창")
                .performTextInput("0925")

            onNodeWithContentDescription("카드 비밀번호 입력창")
                .performTextInput("1234")
        }

        // then
        composeRule
            .onNodeWithContentDescription("카드 추가 완료")
            .assertIsEnabled()
    }
}
