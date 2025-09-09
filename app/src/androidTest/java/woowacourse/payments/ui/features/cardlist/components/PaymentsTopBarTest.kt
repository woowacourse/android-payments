package woowacourse.payments.ui.features.cardlist.components

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentsTopBarTest {
    @get:Rule
    val compose = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun 추가_버튼이_보여야_할_때_버튼이_표시된다() {
        // given
        val addButtonText = context.getString(R.string.card_list_top_bar_add_btn)

        // when
        compose.setContent {
            AndroidpaymentsTheme {
                PaymentsTopBar(
                    onAddClick = {},
                    isAddButtonVisible = true,
                )
            }
        }

        // then
        compose.onNodeWithText(addButtonText).assertIsDisplayed()
    }

    @Test
    fun 추가_버튼이_보이지_않아야_할_때_버튼이_표시되지_않는다() {
        // given
        val addButtonText = context.getString(R.string.card_list_top_bar_add_btn)

        // when
        compose.setContent {
            AndroidpaymentsTheme {
                PaymentsTopBar(
                    onAddClick = {},
                    isAddButtonVisible = false,
                )
            }
        }

        // then
        compose.onNodeWithText(addButtonText).assertDoesNotExist()
    }
}
