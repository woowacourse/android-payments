package woowacourse.payments

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.components.BankSelectRow
import woowacourse.payments.ui.model.toUiModel

@Suppress("ktlint:standard:function-naming")
class BankSelectRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 은행_리스트가_표시된다() {
        val banks = listOf(BankType.BC, BankType.SHINHAN, BankType.KAKAO).map { it.toUiModel() }

        composeTestRule.setContent {
            BankSelectRow(
                banks = banks,
                onSelect = {},
            )
        }

        composeTestRule.onAllNodes(hasTestTag("BankItem")).assertCountEquals(banks.size)
    }

    @Test
    fun 은행_아이템을_클릭하면_onSelect에_해당_은행이_전달된다() {
        val banks = listOf(BankType.BC, BankType.SHINHAN, BankType.KAKAO).map { it.toUiModel() }
        var selected: BankType? = null

        composeTestRule.setContent {
            BankSelectRow(
                banks = banks,
                onSelect = { selected = it },
            )
        }

        val label = "신한카드"
        composeTestRule
            .onNode(hasTestTag("BankItem").and(hasText(label)))
            .performClick()

        assertEquals(BankType.SHINHAN, selected)
    }
}
