package woowacourse.payments.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.Red80
import java.time.YearMonth

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드에_등록한_카드정보가_보인다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
                bankType = BankType.BC,
            )

        // when
        val card = result.getOrNull()

        Assertions.assertNotNull(card)
        composeTestRule.setContent {
            PaymentCard(card = card!!, bankViewType = BankViewType.BC)
        }

        // then
        composeTestRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("12/34")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("뭉치")
            .assertIsDisplayed()
    }

    @Test
    fun `등록되지_않은_카드엔_카드정보가_없다`() {
        // given
        val card = null

        // when
        composeTestRule.setContent {
            PaymentCard(
                card = card,
                bankViewType = BankViewType.BC,
            )
        }

        // then
        composeTestRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("12/34")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("뭉치")
            .assertDoesNotExist()
    }

    @Test
    fun `특정_카드사의_카드색과_카드사_이름이_보인다`() {
        // given
        composeTestRule.setContent {
            PaymentCard(
                modifier = Modifier.testTag("PaymentCard"),
                card = null,
                bankViewType = BankViewType.BC,
            )
        }

        // when
        val card = composeTestRule.onNodeWithTag("PaymentCard")
        val bitmap = card.captureToImage()
        val pixelMap = bitmap.toPixelMap()
        val expectedColor = pixelMap[bitmap.width / 2, bitmap.height / 2]

        // then
        Assertions.assertTrue(expectedColor == Red80)
        composeTestRule.onNodeWithText("BC 카드").assertIsDisplayed()
    }
}
