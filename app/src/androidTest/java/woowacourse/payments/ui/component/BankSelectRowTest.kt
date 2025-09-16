package woowacourse.payments.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var selectedBank: BankTypeUiModel? = null

    @Before
    fun setup() {
        selectedBank = null

        composeTestRule.setContent {
            BankSelectRow(
                onBankSelected = { selectedBank = it },
            )
        }
    }

    @Test
    fun `카드사_목록이_보인다`() {
        // when
        val expected = listOf("BC카드", "하나카드", "현대카드", "카카오카드", "국민카드", "롯데카드", "신한카드", "우리카드")

        // then
        assertAll(
            {
                expected.forEach { name ->
                    composeTestRule.onNodeWithText(name).assertIsDisplayed()
                }
            },
        )
    }

    @Test
    fun `선택한_카드사를_전달한다`() {
        // when
        composeTestRule.onNodeWithText("국민카드").performClick()

        // then
        assertEquals(selectedBank, BankTypeUiModel.KB)
    }
}
