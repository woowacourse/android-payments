import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
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
import woowacourse.payments.ui.components.PaymentCards
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

@Suppress("ktlint:standard:function-naming")
class PaymentCardsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val testClock = Clock.fixed(Instant.parse("2025-01-01T00:00:00Z"), ZoneOffset.UTC)
    private val yearMonth = requireNotNull(ExpirationDateParser.parseOrNull("1226")) // 2026-12

    private val sampleCard =
        Card(
            id = 0L,
            type = CardCompanyType.BC,
            cardNumber = CardNumber.create("1111222233334444"),
            expirationDate = ExpirationDate.create(yearMonth, testClock),
            userName = UserName.create("KIMGAHYUN"),
            password = Password.create("1234"),
        ).toUiModel()

    @Test
    fun 카드_목록이_비어있을_때_카드_추가_안내와_버튼이_표시된다() {
        val cards = emptyList<CardUiModel>()

        composeTestRule.setContent {
            PaymentCards(
                cards = cards,
                showTopAdd = false,
                onAddCardClick = {},
                onCardClick = {},
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
        val cards = listOf(sampleCard)

        composeTestRule.setContent {
            PaymentCards(
                cards = cards,
                showTopAdd = false,
                onAddCardClick = {},
                onCardClick = {},
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
                showTopAdd = true,
                onAddCardClick = {},
                onCardClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("+")
            .assertDoesNotExist()
    }
}
