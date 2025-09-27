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
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.ExpirationDate
import woowacourse.payments.domain.model.Password
import woowacourse.payments.domain.model.UserName
import woowacourse.payments.domain.parser.ExpirationDateParser
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

@Suppress("ktlint:standard:function-naming")
class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val testClock = Clock.fixed(Instant.parse("2025-01-01T00:00:00Z"), ZoneOffset.UTC)
    private val yearMonth = requireNotNull(ExpirationDateParser.parseOrNull("1226")) // 2026-12

    private val card =
        Card(
            id = 0L,
            type = CardCompanyType.BC,
            cardNumber = CardNumber.create("1111222233334444"),
            expirationDate = ExpirationDate.create(yearMonth, testClock),
            userName = UserName.create("KIMGAHYUN"),
            password = Password.create("1234"),
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
        val emptyCard: CardUiModel = CardUiModel.EMPTY

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
