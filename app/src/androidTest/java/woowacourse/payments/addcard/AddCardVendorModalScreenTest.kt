package woowacourse.payments.addcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.addcard.AddCardVendorModalScreen
import woowacourse.payments.ui.addcard.model.VendorModalUiState

@OptIn(ExperimentalTestApi::class)
class AddCardVendorModalScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드사를_클릭하면_지정된_동작이_수행된다() {
        //given
        var isClicked = false
        composeTestRule.setContent {
            AddCardVendorModalScreen(
                vendorModalUiState = VendorModalUiState(),
                onVendorItemClick = {
                    isClicked = true
                }
            )
        }

        //when
        composeTestRule
            .onAllNodesWithContentDescription("카드사 이미지")[0]
            .performClick()

        //then
        assert(isClicked == true)
    }
}