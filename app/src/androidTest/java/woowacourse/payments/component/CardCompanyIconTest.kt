package woowacourse.payments.component

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.component.CardCompanyIcon

class CardCompanyIconTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `은행_아이콘과_이름이_보인다`() {
        // given
        composeTestRule.setContent {
            CardCompanyIcon(
                company = CardCompany.WOORI,
                onClick = {},
                modifier = Modifier.padding(top = 100.dp),
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("우리카드")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("우리카드")
            .assertIsDisplayed()
    }

    @Test
    fun `은행_아이콘을_클릭하면_콜백이_호출된다`() {
        // given
        var clickedBank: CardCompany? = null

        composeTestRule.setContent {
            CardCompanyIcon(
                company = CardCompany.BC,
                onClick = { clickedBank = it },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("우리은행")
            .performClick()

        // then
        assertEquals(clickedBank, CardCompany.BC)
    }
}
