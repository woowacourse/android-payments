package woowacourse.payments.cardform

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardform.CardFormScreen
import java.time.YearMonth

class EditCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var card: Card

    @Before
    fun setUp() {
        card =
            Card
                .create(
                    cardNumber = "1234567812345678",
                    expiryDate = YearMonth.of(2034, 12),
                    cardOwner = "뭉치",
                    password = "1234",
                    bankType = BankType.BC,
                    id = 1L,
                ).getOrNull()!!

        composeTestRule.setContent {
            CardFormScreen(
                card = card,
                onBackClick = {},
                onSaveClick = {},
            )
        }
    }

    @Test
    fun `카드정보를_수정하지_않으면_저장_버튼이_비활성화_된다`() {
        // given

        // when

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsNotEnabled()
    }

    @Test
    fun `유효하지_않은_카드정보로_수정하면_저장_버튼이_비활성화_된다`() {
        // given
        val expiryDateInputField = composeTestRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        expiryDateInputField.performTextClearance()
        expiryDateInputField.performTextInput("123")

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsNotEnabled()
    }

    @Test
    fun `카드정보를_수정하면_저장_버튼이_활성화_된다`() {
        // given
        val cardNumberInputField = composeTestRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        cardNumberInputField.performTextClearance()
        cardNumberInputField.performTextInput("1234567811111111")

        // then
        composeTestRule.onNodeWithContentDescription("완료").assertIsEnabled()
    }
}
