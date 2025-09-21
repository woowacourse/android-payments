package woowacourse.payments.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cards.CardsScreen
import woowacourse.payments.ui.cards.CardsStateHolder
import java.time.YearMonth

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val stateHolder = CardsStateHolder()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardsScreen(stateHolder = stateHolder, onCardAddClick = { })
        }
    }

    @Test
    fun `등록된_카드가_0개이면_등록해주세요_문구와_카드추가_이미지버튼이_보인다`() {
        // given

        // when & then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요.").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("카드 추가").assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_1개_이상이면_상단바에_추가_버튼이_보이지_않는다`() {
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
        stateHolder.cardsState.add(card!!)

        // then
        composeTestRule.onNodeWithText("추가").assertIsNotDisplayed()
    }

    @Test
    fun `등록된_카드가_2개_이상이면_카드추가_이미지_버튼이_보이지_않고_상단바에_추가버튼이_보인다`() {
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
        stateHolder.cardsState.addAll(listOf(card!!, card))

        // then
        composeTestRule.onNodeWithContentDescription("카드 추가").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("추가").assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_화면에_보인다`() {
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
        stateHolder.cardsState.add(card!!)

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
    fun `카드_추가_화면에_타이틀과_추가버튼과_등록된_카드가_보인다`() {
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
        stateHolder.cardsState.add(card!!)

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
        composeTestRule.onNodeWithContentDescription("카드 추가").assertIsDisplayed()
        composeTestRule.onNodeWithText("Payments").assertIsDisplayed()
    }
}
