package woowacourse.payments.cardaddition.component

import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
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
    fun `카드_번호_입력_후_키보드의_다음_버튼을_누르면_만료일_입력창으로_넘어간다`() {
        // when
        composeRule
            .onNodeWithText("카드 번호")
            .performImeAction()

        // then
        composeRule
            .onNodeWithText("만료일")
            .assertIsFocused()
    }

    @Test
    fun `만료일_입력_후_키보드의_다음_버튼을_누르면_카드_소유자_이름_입력창으로_넘어간다`() {
        // when
        composeRule
            .onNodeWithText("만료일")
            .performImeAction()

        // then
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsFocused()
    }

    @Test
    fun `카드_소유자_이름_입력_후_키보드의_다음_버튼을_누르면_비밀번호_입력창으로_넘어간다`() {
        // when
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performImeAction()

        // then
        composeRule
            .onNodeWithText("비밀번호")
            .assertIsFocused()
    }
}
