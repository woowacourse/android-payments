package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.features.addcard.AddCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.AppTestTags
import woowacourse.payments.ui.util.AppTestTags.CARD_EXPIRY_DATE_FIELD
import woowacourse.payments.ui.util.AppTestTags.CARD_NUMBER_FIELD
import woowacourse.payments.ui.util.AppTestTags.CARD_OWNER_NAME_FIELD
import woowacourse.payments.ui.util.AppTestTags.CARD_PASSWORD_FIELD

class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onNavigateBack = { },
                    onNavigateSave = { },
                )
            }
        }
    }

    @Test
    fun 카드_추가_화면의_모든_입력_필드가_표시된다() {
        // then
        composeTestRule.onNodeWithTag(CARD_NUMBER_FIELD).assertExists()
        composeTestRule.onNodeWithTag(CARD_EXPIRY_DATE_FIELD).assertExists()
        composeTestRule.onNodeWithTag(CARD_OWNER_NAME_FIELD).assertExists()
        composeTestRule.onNodeWithTag(CARD_PASSWORD_FIELD).assertExists()
    }

    @Test
    fun 카드_번호_입력_필드에_입력값이_구분자를_포함하여_표한된다() {
        // given
        val cardNumberField = composeTestRule.onNodeWithTag(CARD_NUMBER_FIELD)

        // when
        cardNumberField.performTextInput("1234123412341234")

        // then
        cardNumberField.assertExists()
        composeTestRule.onNodeWithText("1234 - 1234 - 1234 - 1234").assertIsDisplayed()
    }

    @Test
    fun 카드_번호_입력_필드에_값을_일부_입력시_입력값이_구분자를_포함하여_표한된다() {
        // given
        val cardNumberField = composeTestRule.onNodeWithTag(CARD_NUMBER_FIELD)

        // when
        cardNumberField.performTextInput("12341234")

        // then
        cardNumberField.assertExists()
        composeTestRule.onNodeWithText("1234 - 1234").assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름을_입력하면_글자_수가_업데이트_된다() {
        // given
        val ownerNameField = composeTestRule.onNodeWithTag(CARD_OWNER_NAME_FIELD)

        // when
        ownerNameField.performTextInput("WOOWA")

        // then
        composeTestRule.onNodeWithText("5/30").assertIsDisplayed()
    }

    @Test
    fun 유효한_만료일을_입력하면_에러_메시지가_표시되지_않는다() {
        // given
        val expireDateField = composeTestRule.onNodeWithTag(CARD_EXPIRY_DATE_FIELD)

        // when
        expireDateField.performTextInput("1242")

        // then
        composeTestRule
            .onNodeWithTag(AppTestTags.EXPIRE_DATE_ERROR_TEXT)
            .assertDoesNotExist()
    }

    @Test
    fun 유효하지_않은_만료일을_입력하면_에러_메시지가_표시된다() {
        // given
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expireDateField = composeTestRule.onNodeWithTag(CARD_EXPIRY_DATE_FIELD)

        // when
        expireDateField.performTextInput("1342")

        // then
        val expectedErrorMessage =
            context.getString(R.string.add_card_expire_date_month_error_message)
        composeTestRule.onNodeWithText(expectedErrorMessage).assertIsDisplayed()
    }

    @Test
    fun 만료일이_완전히_입력되지_않으면_에러_메시지가_표시되지_않는다() {
        // given
        val expireDateField = composeTestRule.onNodeWithTag(CARD_EXPIRY_DATE_FIELD)

        // when
        expireDateField.performTextInput("999")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다").assertDoesNotExist()
    }
}
