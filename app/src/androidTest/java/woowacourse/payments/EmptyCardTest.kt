package woowacourse.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.EmptyCard

class EmptyCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emptyCard_showsAddIcon_andHandlesClick() {
        var clicked = false
        composeTestRule.setContent {
            EmptyCard(onClick = { clicked = true })
        }

        // 아이콘 존재 여부 확인
        composeTestRule
            .onNodeWithContentDescription("AddCardIcon")
            .assertExists()

        // 클릭 이벤트 검증
        composeTestRule
            .onNodeWithContentDescription("AddCardIcon")
            .performClick()

        assert(clicked)
    }
}
