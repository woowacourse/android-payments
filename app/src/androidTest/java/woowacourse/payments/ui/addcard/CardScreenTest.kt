package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.CardScreenCategory

class CardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupCardScreen(
        card: Card = Card(),
        cardScreenCategory: CardScreenCategory = CardScreenCategory.Add,
        onCardChange: (Card) -> Unit = {},
        onBackClick: () -> Unit = {},
        onSaveClick: () -> Unit = {},
        isCardSavable: Boolean = false,
        isSheetVisible: Boolean = false,
        onChangeSheetVisible: (Boolean) -> Unit = {},
    ) {
        composeTestRule.setContent {
            CardScreen(
                card = card,
                cardScreenCategory = cardScreenCategory,
                onCardChange = onCardChange,
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
                isCardSavable = isCardSavable,
                isSheetVisible = isSheetVisible,
                onChangeSheetVisible = onChangeSheetVisible,
            )
        }
    }

    @Test
    fun `CardScreen_카테고리가_추가_라면_카드_추가가_표시된다`() {
        // given + when
        setupCardScreen(cardScreenCategory = CardScreenCategory.Add)

        // then
        composeTestRule.onNodeWithText("카드 추가").assertIsDisplayed()
    }

    @Test
    fun `CardScreen_카테고리가_수정_라면_카드_수정이_표시된다`() {
        // given + when
        setupCardScreen(cardScreenCategory = CardScreenCategory.Edit)

        // then
        composeTestRule.onNodeWithText("카드 수정").assertIsDisplayed()
    }

    @Test
    fun `뒤로가기_버튼_클릭_시_onBackClick이_호출된다`() {
        // given
        var backClicked = false
        setupCardScreen(onBackClick = { backClicked = true })

        // when
        composeTestRule.onNodeWithContentDescription("뒤로 가기").performClick()

        // then
        assert(backClicked)
    }

    @Test
    fun `저장_버튼_클릭_시_isCardSavable이_true이면_onSaveClick이_호출된다`() {
        // given
        var saveClicked = false
        setupCardScreen(onSaveClick = { saveClicked = true }, isCardSavable = true)

        // when
        composeTestRule.onNodeWithContentDescription("저장").performClick()

        // then
        assert(saveClicked)
    }

    @Test
    fun `저장_버튼_클릭_시_isCardSavable이_false이면_onSaveClick이_호출되지_않는다`() {
        // given
        var saveClicked = false
        setupCardScreen(onSaveClick = { saveClicked = true }, isCardSavable = false)

        // when
        composeTestRule.onNodeWithContentDescription("저장").performClick()

        // then
        assert(!saveClicked)
    }

    @Test
    fun `isCardSavable이_true이면_저장_버튼이_활성화된다`() {
        // given + when
        setupCardScreen(isCardSavable = true)

        // then
        composeTestRule.onNodeWithContentDescription("저장").assertIsEnabled()
    }

    @Test
    fun `isCardSavable이_false이면_저장_버튼이_비활성화된다`() {
        // given + when
        setupCardScreen(isCardSavable = false)

        // then
        composeTestRule.onNodeWithContentDescription("저장").assertIsNotEnabled()
    }

    @Test
    fun `카드_번호_입력_시_onCardChange가_호출된다`() {
        // given
        var changedCard: Card? = null
        setupCardScreen(onCardChange = { newCard -> changedCard = newCard })

        // when
        composeTestRule.onNodeWithText("카드 번호").performTextInput("1234123412341234")

        // then
        assert(changedCard?.number == CardNumber("1234", "1234", "1234", "1234"))
    }

    @Test
    fun `만료일_입력_시_onCardChange가_호출된다`() {
        // given
        var changedCard: Card? = null
        setupCardScreen(onCardChange = { newCard -> changedCard = newCard })

        // when
        composeTestRule.onNodeWithText("만료일").performTextInput("1234")

        // then
        assert(changedCard?.expirationDate == CardExpirationDate("12", "34"))
    }

    @Test
    fun `카드_소유자_이름_입력_시_onCardChange가_호출된다`() {
        // given
        var changedCard: Card? = null
        setupCardScreen(onCardChange = { newCard -> changedCard = newCard })

        // when
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").performTextInput("TEST")

        // then
        assert(changedCard?.ownerName == OwnerName("TEST"))
    }

    @Test
    fun `비밀번호_입력_시_onCardChange가_호출된다`() {
        // given
        var changedCard: Card? = null
        setupCardScreen(onCardChange = { newCard -> changedCard = newCard })

        // when
        composeTestRule.onNodeWithText("비밀번호").performTextInput("1234")

        // then
        assert(changedCard?.password == Password("1234"))
    }

    @Test
    fun `BankSelectBottomSheet에서_은행_선택_시_onCardChange가_호출된다`() {
        // given
        var changedCard: Card? = null
        setupCardScreen(
            onCardChange = { newCard -> changedCard = newCard },
            isSheetVisible = true,
            onChangeSheetVisible = { it ->
                if (it) {
                    composeTestRule.setContent {
                        BankSelectBottomSheet(onDismiss = {}, onBankSelect = { bankType ->
                            changedCard = Card(bank = bankType)
                        })
                    }
                }
            },
        )

        // when
        composeTestRule.onNodeWithText("국민카드").performClick()

        // then
        assert(changedCard?.bank == BankType.KB)
    }
}
