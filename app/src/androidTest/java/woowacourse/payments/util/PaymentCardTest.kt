package woowacourse.payments.util

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.newcard.CardCompanyUiState

class PaymentCardTest {
    @get:Rule
    val composeTestRule =
        createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PaymentCard(
                card =
                    Card(
                        cardNumber = CardNumber("1234567812345678"),
                        expiredDate = ExpiredDate.of(1, 26)!!,
                        ownerName = OwnerName("크림"),
                        password = Password("1234"),
                        cardCompany = CardCompany.HYUNDAI,
                    ),
                cardCompanyUiState = CardCompanyUiState.from(CardCompany.HYUNDAI),
            )
        }
    }

    @Test
    fun `카드에는_마지막_8글자가_가려진_형식의_번호가_표시된다`() {
        composeTestRule
            .onNodeWithText("1234-5678-****-****", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `카드에는_소유자명이_표시된다`() {
        composeTestRule.onNodeWithText("크림", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `카드에는_만료일이_표시된다`() {
        composeTestRule.onNodeWithText("01 / 26", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `카드에는_카드사가_표시된다`() {
        composeTestRule.onNodeWithText("현대카드", useUnmergedTree = true).assertIsDisplayed()
    }
}
