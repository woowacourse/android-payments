package woowacourse.payments.ui.cardList.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class RegistrationCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `추가_버튼이_화면에_보인다`() {
        // given:
        composeTestRule.setContent {
            RegistrationCard(onRegistrationClick = {})
        }

        // then:
        composeTestRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .assertExists()
    }

    @Test
    fun `추가_버튼_클릭_시_onRegistrationClick_이_호출된다`() {
        // given:
        var clicked = false
        composeTestRule.setContent {
            RegistrationCard(onRegistrationClick = { clicked = true })
        }

        // when:
        composeTestRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .performClick()

        // then:
        assertThat(clicked).isTrue()
    }
}
