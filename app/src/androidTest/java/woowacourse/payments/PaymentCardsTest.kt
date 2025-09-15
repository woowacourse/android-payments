import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.ExpirationDate
import woowacourse.payments.domain.model.Password
import woowacourse.payments.domain.model.UserName
import woowacourse.payments.ui.components.PaymentCards
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.text.ExpirationDateInputParser

@Suppress("ktlint:standard:function-naming")
class PaymentCardsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val yearMonth = ExpirationDateInputParser.parse("1226")
    private val sampleCard =
        Card(
            cardNumber = CardNumber.from("1111222233334444"),
            expirationDate = ExpirationDate.from(yearMonth),
            userName = UserName.from("KIMGAHYUN"),
            password = Password.from("1234"),
        ).toUiModel()

    @Test
    fun 카드_목록이_비어있을_때_카드_추가_안내와_버튼이_표시된다() {
        val cards = emptyList<CardUiModel>()

        composeTestRule.setContent {
            PaymentCards(
                cards = cards,
                onAddCardClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("+")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_개수가_1개_있을_때_카드_추가_버튼이_하단에_표시된다() {
        val cards = listOf(sampleCard, sampleCard)

        composeTestRule.setContent {
            PaymentCards(
                cards = cards,
                onAddCardClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("+")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_카드가_2개_이상_있을_때_카드_추가_UI는_상단바에_노출된다() {
        val cards = listOf(sampleCard, sampleCard)

        composeTestRule.setContent {
            PaymentCards(
                cards = cards,
                onAddCardClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("+")
            .assertIsNotDisplayed()
    }
}
