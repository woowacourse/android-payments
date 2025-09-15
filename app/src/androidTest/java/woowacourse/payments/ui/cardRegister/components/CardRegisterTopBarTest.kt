package woowacourse.payments.ui.cardRegister.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class CardRegisterTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `타이틀이_표시된다`() {
        // given:
        composeTestRule.setContent {
            CardRegisterTopBar(onBackClick = {}, onSaveClick = {})
        }

        // then:
        composeTestRule
            .onNodeWithText("카드 추가")
            .assertExists()
    }

    @Test
    fun `뒤로가기_버튼_클릭_시_onBackClick_이_호출된다`() {
        // given:
        var clicked = false
        composeTestRule.setContent {
            CardRegisterTopBar(
                onBackClick = { clicked = true },
                onSaveClick = {},
            )
        }

        // when:
        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .performClick()

        // then:
        assertThat(clicked).isTrue()
    }

    @Test
    fun `완료_버튼_클릭_시_onSaveClick_이_호출된다`() {
        // given:
        var clicked = false
        composeTestRule.setContent {
            CardRegisterTopBar(
                onBackClick = {},
                onSaveClick = { clicked = true },
            )
        }

        // when:
        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        // then:
        assertThat(clicked).isTrue()
    }
}
