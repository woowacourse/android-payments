package woowacourse.payments.ui.cardRegister

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class CardRegisterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `뒤로가기_버튼_클릭_시_onBackClick_호출된다`() {
        // given:
        var backClicked = false
        composeTestRule.setContent {
            CardRegisterScreen(
                onBackClick = { backClicked = true },
                onSaveClick = {},
                isNotValidInput = {},
                isNotChangedInput = {},
                editMode = false,
                onEditingSaveClick = {},
            )
        }

        // when:
        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .performClick()

        // then:
        assertThat(backClicked).isEqualTo(true)
    }

    @Test
    fun `유효하지_않은_입력_일_때_저장_버튼_클릭_시_isNotValidInput_호출된다`() {
        // given:
        var invalidInputCalled = false
        composeTestRule.setContent {
            CardRegisterScreen(
                onBackClick = {},
                onSaveClick = {},
                isNotValidInput = { invalidInputCalled = true },
                isNotChangedInput = {},
                editMode = false,
                onEditingSaveClick = {},
            )
        }

        // when:
        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        // then:
        assertThat(invalidInputCalled).isEqualTo(true)
    }

    @Test
    fun `유효한_입력_일_때_저장_버튼_클릭_시_onSaveClick_호출된다`() {
        // given:
        var savedCard = false
        composeTestRule.setContent {
            CardRegisterScreen(
                onBackClick = {},
                onSaveClick = { card ->
                    if (card.number == "1111222233334444") savedCard = true
                },
                isNotValidInput = {},
                isNotChangedInput = {},
                editMode = false,
                onEditingSaveClick = {},
            )
        }

        // when:
        composeTestRule.onNodeWithText("카드 번호").performTextInput("1111222233334444")
        composeTestRule.onNodeWithText("만료일").performTextInput("0426")
        composeTestRule.onNodeWithText("비밀번호").performTextInput("1234")

        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        // then:
        assertThat(savedCard).isEqualTo(true)
    }
}
