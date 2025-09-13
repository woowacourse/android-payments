package woowacourse.payments.ui.cards.components

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.core.app.ActivityOptionsCompat
import org.junit.Rule
import org.junit.Test

class CardsTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드를_추가할_수_있으면_추가_버튼이_뜬다`() {
        // when
        composeTestRule.setContent {
            CardsTopBar(
                onAddClick = {},
                isAddable = true,
            )
        }

        // then
        composeTestRule.onNodeWithText("추가").assertIsDisplayed()
    }

    @Test
    fun `카드를_추가할_수_없으면_추가_버튼이_뜨지_않는다`() {
        // when
        composeTestRule.setContent {
            CardsTopBar(
                onAddClick = {},
                isAddable = false,
            )
        }

        // then
        composeTestRule.onNodeWithText("추가").assertIsNotDisplayed()
    }
}
