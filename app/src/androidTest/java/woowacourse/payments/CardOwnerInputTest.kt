package woowacourse.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.ui.component.CardOwnerInputField

class CardOwnerInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var cardOwner by remember { mutableStateOf("") }

            CardOwnerInputField(
                cardOwner = CardOwner(cardOwner),
                onOwnerChange = { cardOwner = it?.value ?: "" },
            )
        }
    }

    @Test
    fun 초기_화면에_카드_소유자_이름_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유자를_입력하면_화면에_표시된다() {
        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput("Meeple")

        // then
        composeTestRule
            .onNodeWithText("Meeple")
            .assertIsDisplayed()
    }
}
