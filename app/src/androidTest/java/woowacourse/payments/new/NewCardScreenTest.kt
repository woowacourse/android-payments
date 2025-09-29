package woowacourse.payments.new

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.state.CardCompanyState
import woowacourse.payments.ui.view.new.NewCardMode
import woowacourse.payments.ui.view.new.NewCardScreen
import woowacourse.payments.ui.view.new.NewCardUiEvent
import woowacourse.payments.ui.view.new.NewCardUiState
import woowacourse.payments.ui.view.new.NewCardUiStateHolder.Companion.NewCardUiStateHolder

class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    fun setupDefaultComposeTestRule() {
        composeTestRule.setContent {
            NewCardScreen(
                uiState =
                    NewCardUiState(
                        number = "1234123412341234",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "0908",
                        CardCompanyState.Selected(CardCompany.BC),
                    ),
                onClickCard = {},
                onCardChange = {},
            )
        }
    }

    @Test
    fun `카드번호를_입력하면_구분자에_따라_자동으로_분리된다`() {
        // given
        setupDefaultComposeTestRule()
        val cardNumber = "1234123412341234"

        // when
        composeTestRule
            .onNode(hasText("카드 번호") and hasSetTextAction())
            .performTextInput(cardNumber)

        // then
        composeTestRule
            .onNodeWithText("1234 - 1234 - 1234 - 1234")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일을_입력하면_구분자로_분리된다`() {
        // given
        setupDefaultComposeTestRule()
        val expireDate = "0908"

        // when
        composeTestRule
            .onNode(
                hasText("만료일") and hasSetTextAction(),
            ).performTextInput(expireDate)

        // then
        composeTestRule
            .onNodeWithText("09 / 08")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자의_이름_길이가_출력된다`() {
        // given
        setupDefaultComposeTestRule()
        val name = "peto"

        // when
        composeTestRule
            .onNode(
                hasText("카드 소유자 이름(선택)") and hasSetTextAction(),
            ).performTextInput(name)

        // then
        composeTestRule
            .onNodeWithText("4 / 30")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_암호화된다`() {
        // given
        setupDefaultComposeTestRule()
        val password = "0908"

        // when
        composeTestRule
            .onNode(
                hasText("비밀번호") and hasSetTextAction(),
            ).performTextInput(password)

        // then
        composeTestRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }

    @Test
    fun `Modify모드에서는_등록된_카드_정보가_보여야한다`() {
        val card =
            Card(
                "1234123412341234",
                "0908",
                "peto123",
                "1234",
                CardCompany.BC,
            )
        composeTestRule.setContent {
            NewCardScreen(
                uiState =
                    NewCardUiState(
                        card.number,
                        card.expireDate,
                        card.ownerName,
                        card.password,
                        CardCompanyState.Selected(card.company),
                        mode = NewCardMode.Modify(card.toSerializationCard(), 0),
                    ),
                onCardChange = {},
                onClickCard = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("BC카드").assertIsDisplayed()
        composeTestRule.onNodeWithText("1234 - 1234 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("0908").assertIsDisplayed()
    }

    @Test
    fun `카드를_클릭하면_BottomSheet가_열린다`() {
        // given
        var bottomSheetOpened = false
        composeTestRule.setContent {
            NewCardScreen(
                uiState =
                    NewCardUiState(
                        "1234123412341234",
                        "",
                        "peto",
                        "",
                        CardCompanyState.Selected(CardCompany.BC),
                        NewCardMode.Add,
                    ),
                onClickCard = { bottomSheetOpened = true },
                onCardChange = {},
            )
        }

        // when
        composeTestRule.onNodeWithText("BC카드").performClick()

        // then
        composeTestRule.runOnIdle {
            assert(bottomSheetOpened)
        }
    }

    @Test
    fun `기존에_등록된_카드_정보가_수정되었다면_참을_반환한다`() {
        // given
        val origin =
            NewCardMode.Modify(
                Card(
                    "1234123412341234",
                    "0908",
                    "peto",
                    "1234",
                    CardCompany.BC,
                ).toSerializationCard(),
                0,
            )
        val holder = NewCardUiStateHolder(origin)

        // 초기 상태는 false
        assertFalse(holder.uiState.isModified())

        // when & then (수정된 경우 → true)
        listOf(
            NewCardUiEvent.OnChangeCardCompany(CardCompanyState.Selected(CardCompany.KB)),
            NewCardUiEvent.OnChangeCardNumber("111111111111"),
            NewCardUiEvent.OnChangeExpireDate("0909"),
            NewCardUiEvent.OnChangeOwnerName("peto123"),
        ).forEach { event ->
            holder.modifyUiState(event)
            assertTrue(holder.uiState.isModified())
        }
    }

    @Test
    fun `기존에_등록된_카드_정보가_수정되지_않았다면_거짓을_반환한다`() {
        // given
        val origin =
            NewCardMode.Modify(
                Card(
                    "1234123412341234",
                    "0908",
                    "peto",
                    "1234",
                    CardCompany.BC,
                ).toSerializationCard(),
                0,
            )
        val holder = NewCardUiStateHolder(origin)

        assertFalse(holder.uiState.isModified())

        // then
        listOf(
            NewCardUiEvent.OnChangeCardCompany(CardCompanyState.Selected(CardCompany.BC)),
            NewCardUiEvent.OnChangeCardNumber("1234123412341234"),
            NewCardUiEvent.OnChangeExpireDate("0908"),
            NewCardUiEvent.OnChangeOwnerName("peto"),
        ).forEach { event ->
            holder.modifyUiState(event)
            assertFalse(holder.uiState.isModified())
        }
    }
}
