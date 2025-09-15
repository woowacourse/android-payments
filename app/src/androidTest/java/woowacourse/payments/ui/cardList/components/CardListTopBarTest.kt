package woowacourse.payments.ui.cardList.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class CardListTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `앱_이름이_타이틀에_표시된다`() {
        // given:
        composeTestRule.setContent {
            CardListTopBar(onRegistrationClick = {})
        }

        // then:
        composeTestRule.onNodeWithText("Payments").assertExists()
    }

    @Test
    fun `추가_버튼이_기본적으로_보인다`() {
        // given:
        composeTestRule.setContent {
            CardListTopBar(onRegistrationClick = {})
        }

        // then:
        composeTestRule.onNodeWithText("추가").assertExists()
    }

    @Test
    fun `추가_버튼_클릭_시_onRegistrationClick_이_호출된다`() {
        // given:
        var clicked = false
        composeTestRule.setContent {
            CardListTopBar(onRegistrationClick = { clicked = true })
        }

        // when:
        composeTestRule.onNodeWithText("추가").performClick()

        // then:
        assertThat(clicked).isTrue()
    }

    @Test
    fun `isShowRegistrationButton_이_false_면_등록_버튼이_보이지_않는다`() {
        // given:
        composeTestRule.setContent {
            CardListTopBar(onRegistrationClick = {}, isShowRegistrationButton = false)
        }

        // then:
        composeTestRule.onNodeWithText("추가").assertDoesNotExist()
    }
}
