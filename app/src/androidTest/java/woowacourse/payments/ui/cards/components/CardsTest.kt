package woowacourse.payments.ui.cards.components

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.core.app.ActivityOptionsCompat
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.HWANNOW_CARD
import woowacourse.payments.ui.JUNSEO511_CARD
import woowacourse.payments.ui.cards.CardsStateHolder

class CardsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_목록이_보인다`() {
        // when
        composeTestRule.setContent {
            Cards(
                scrollState = rememberScrollState(),
                onAddClick = {},
                cardsStateHolder = CardsStateHolder(listOf(HWANNOW_CARD)),
                onEditClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("김환노")
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_한장도_없을_경우_카드_등록_문구_및_추가_이미지가_보인다`() {
        // when
        composeTestRule.setContent {
            Cards(
                scrollState = rememberScrollState(),
                onAddClick = {},
                onEditClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 등록 이미지 버튼")
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_한장_있을_경우_카드_추가_이미지가_보인다`() {
        // when
        composeTestRule.setContent {
            Cards(
                scrollState = rememberScrollState(),
                onAddClick = {},
                cardsStateHolder = CardsStateHolder(listOf(HWANNOW_CARD)),
                onEditClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드 등록 이미지 버튼")
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_한장_이상_있을_경우_카드_등록_문구가_보이지_않는다`() {
        // when
        composeTestRule.setContent {
            Cards(
                scrollState = rememberScrollState(),
                onAddClick = {},
                cardsStateHolder = CardsStateHolder(listOf(HWANNOW_CARD)),
                onEditClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsNotDisplayed()
    }

    @Test
    fun `카드가_여러장_있을_경우_카드_추가_이미지가_뜨지_않는다`() {
        // when
        composeTestRule.setContent {
            Cards(
                scrollState = rememberScrollState(),
                onAddClick = {},
                cardsStateHolder = CardsStateHolder(listOf(HWANNOW_CARD, JUNSEO511_CARD)),
                onEditClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드 등록 이미지 버튼")
            .assertIsNotDisplayed()
    }
}
