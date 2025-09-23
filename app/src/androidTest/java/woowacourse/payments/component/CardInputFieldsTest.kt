package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.CardInputFieldStateHolder
import woowacourse.payments.ui.component.CardInputFields
import java.time.YearMonth

class CardInputFieldsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var card: Card
    private lateinit var stateHolder: CardInputFieldStateHolder

    @Before
    fun setUp() {
        card =
            Card
                .create(
                    cardNumber = "1234567812345678",
                    expiryDate = YearMonth.of(2095, 12),
                    cardOwner = "뭉치",
                    password = "1234",
                    bankType = BankType.BC,
                ).getOrNull()!!

        stateHolder = CardInputFieldStateHolder()

        composeTestRule.setContent {
            CardInputFields(stateHolder = stateHolder)
        }

        stateHolder.setupRegisteredCardInfo(card)
    }

    @Test
    fun `선택한_카드의_카드번호가_입력필드에_자동입력된다`() {
        // given
        composeTestRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when & then
        composeTestRule
            .onNodeWithText("1234-5678-1234-5678")
            .assertIsDisplayed()
    }

    @Test
    fun `선택한_카드의_만료일이_입력필드에_자동입력된다`() {
        // given
        composeTestRule.onNode(hasText("만료일") and hasSetTextAction())

        // when & then
        composeTestRule
            .onNodeWithText("12/95")
            .assertIsDisplayed()
    }

    @Test
    fun `선택한_카드의_카드소유자_이름이_입력필드에_자동입력된다`() {
        // given
        composeTestRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())

        // when & then
        composeTestRule
            .onNodeWithText("뭉치")
            .assertIsDisplayed()
    }

    @Test
    fun `선택한_카드의_비밀번호가_입력필드에_자동입력된다`() {
        // given
        composeTestRule.onNode(hasText("비밀번호") and hasSetTextAction())

        // when & then
        composeTestRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }
}
