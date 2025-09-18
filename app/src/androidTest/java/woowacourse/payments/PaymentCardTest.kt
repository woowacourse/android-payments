package woowacourse.payments

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.ExpirationDate
import woowacourse.payments.domain.model.Password
import woowacourse.payments.domain.model.UserName
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.text.ExpirationDateInputParser

@Suppress("ktlint:standard:function-naming")
class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val yearMonth = ExpirationDateInputParser.parse("1226")
    private val card =
        Card(
            type = BankType.BC,
            cardNumber = CardNumber.from("1111222233334444"),
            expirationDate = ExpirationDate.from(yearMonth),
            userName = UserName.from("KIMGAHYUN"),
            password = Password.from("1234"),
        ).toUiModel()

    @Test
    fun 카드_정보가_존재하면_카드번호_만료일_사용자_이름이_화면에_표시된다() {
        composeTestRule.setContent {
            Box(Modifier.testTag("PaymentCardContainer")) {
                PaymentCard(card = card)
            }
        }

        composeTestRule.onNodeWithTag("PaymentCardContainer").assertIsDisplayed()
        composeTestRule.onNodeWithText("BC카드").assertIsDisplayed()
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("12 / 26").assertIsDisplayed()
        composeTestRule.onNodeWithText("KIMGAHYUN").assertIsDisplayed()
    }

    @Test
    fun 카드에_정보가_없으면_컨테이너만_표시되고_값_텍스트는_없다() {
        val emptyCard: CardUiModel = CardUiModel("", "", "", "")

        composeTestRule.setContent {
            Box(Modifier.testTag("PaymentCardContainer")) {
                PaymentCard(card = emptyCard)
            }
        }

        composeTestRule.onNodeWithTag("PaymentCardContainer").assertIsDisplayed()

        composeTestRule.onNodeWithText(" - ").assertDoesNotExist()
        composeTestRule.onNodeWithText(" / ").assertDoesNotExist()
    }
}
