package woowacourse.payments.cards.component

import androidx.activity.ComponentActivity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.cards.component.SelectBankBottomSheet

@RunWith(Parameterized::class)
class SelectBankBottomSheetTest(
    private val bankViewType: BankViewType,
) {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Before
    fun setUp() {
        composeTestRule.setContent {
            SelectBankBottomSheet(
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                onBankSelectClick = {},
                onDismissRequest = {},
            )
        }
    }

    @Test
    fun `선택_가능한_카드사_이미지들이_보인다`() {
        // given

        // when

        // then
        composeTestRule.onNodeWithContentDescription(bankViewType.name).assertIsDisplayed()
    }

    @Test
    fun `선택_가능한_카드사_이름들이_보인다`() {
        // given
        val context = composeTestRule.activity

        // when

        // then
        composeTestRule
            .onNodeWithText(context.getString(bankViewType.nameRes!!))
            .assertIsDisplayed()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = BankViewType.entries.toTypedArray().filter { it != BankViewType.NONE }
    }
}
