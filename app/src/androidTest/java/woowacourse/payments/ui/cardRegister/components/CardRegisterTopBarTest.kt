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
    fun `편집_모드_일_경우_카드_수정_타이틀이_표시된다`() {
        // given:
        composeTestRule.setContent {
            CardRegisterTopBar(onBackClick = {}, onSaveClick = {}, editMode = true)
        }

        // then:
        composeTestRule
            .onNodeWithText("카드 수정")
            .assertExists()
    }

    @Test
    fun `편집_모드가_아닐_경우_카드_추가_타이틀이_표시된다`() {
        // given:
        composeTestRule.setContent {
            CardRegisterTopBar(onBackClick = {}, onSaveClick = {}, editMode = false)
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
                editMode = true,
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
                editMode = true,
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
