package woowacourse.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.screen.AddCardContent
import woowacourse.payments.ui.screen.AddCardUiState

@Suppress("ktlint:standard:function-naming")
class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setup() {
        composeTestRule.setContent {
            var uiState by remember { mutableStateOf(AddCardUiState(showCompanySheet = false)) }
            val cardPreview = CardUiModel.EMPTY

            AddCardContent(
                uiState = uiState,
                cardPreview = cardPreview,
                onBackPressed = {},
                onNumberChange = { value -> uiState = uiState.copy(number = value) },
                onExpirationChange = { value -> uiState = uiState.copy(expiration = value) },
                onUserNameChange = { value -> uiState = uiState.copy(userName = value) },
                onPasswordChange = { value -> uiState = uiState.copy(password = value) },
                onDismissSheet = { uiState = uiState.copy(showCompanySheet = false) },
                onSelectCompany = {},
                onSaveClick = {},
            )
        }
    }

    @Test
    fun 카드를_추가하기_위한_필드들이_보인다() {
        setup()
        composeTestRule.onNodeWithText("카드 번호").assertIsDisplayed()
        composeTestRule.onNodeWithText("만료일").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").assertIsDisplayed()
        composeTestRule.onNodeWithText("비밀번호").assertIsDisplayed()
    }

    @Test
    fun 카드번호는_4자리마다_하이픈으로_자동_포맷된다() {
        setup()
        val field = composeTestRule.onNodeWithText("카드 번호")
        field.performTextInput("1234123412341234")
        composeTestRule.onNodeWithText("1234 - 1234 - 1234 - 1234").assertIsDisplayed()
    }

    @Test
    fun 카드번호는_숫자만_허용한다() {
        setup()
        val field = composeTestRule.onNodeWithText("카드 번호")
        field.performTextInput("abcd123")
        composeTestRule.onNodeWithText("123").assertIsDisplayed()
    }

    @Test
    fun 만료일은_2자리마다_슬래시로_자동_포맷된다() {
        setup()
        val field = composeTestRule.onNodeWithText("만료일")
        field.performTextInput("1226")
        composeTestRule.onNodeWithText("12 / 26").assertIsDisplayed()
    }

    @Test
    fun 만료일은_숫자만_허용한다() {
        setup()
        val field = composeTestRule.onNodeWithText("만료일")
        field.performTextInput("aabb1")
        composeTestRule.onNodeWithText("1").assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름은_정확히_30자까지_입력_되고_카운터가_30_30을_표시한다() {
        setup()
        val field = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
        val name = "a".repeat(30)
        field.performTextInput(name)
        composeTestRule.onNodeWithText(name).assertIsDisplayed()
        composeTestRule.onNodeWithText("30 / 30").assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름을_입력하면_입력값과_글자수가_표시된다() {
        setup()
        val field = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
        field.performTextInput("kimgahyun")
        composeTestRule.onNodeWithText("kimgahyun").assertIsDisplayed()
        composeTestRule.onNodeWithText("9 / 30").assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_마스킹되어_표시된다() {
        setup()
        val field = composeTestRule.onNodeWithText("비밀번호")
        field.performTextInput("1234")
        composeTestRule.onNodeWithText("••••").assertIsDisplayed()
    }

    @Test
    fun 수정할_카드가_있는_경우_카드_정보를_바탕으로_화면이_보여진다() {
        composeTestRule.setContent {
            var uiState by remember { mutableStateOf(AddCardUiState(showCompanySheet = false)) }
            val cardPreview =
                CardUiModel(
                    cardCompany = CardCompanyType.BC.toUiModel(),
                    cardNumberRaw = "1111222233334444",
                    expirationDateRaw = "1199",
                    userName = "JOYKIM",
                    password = "1111",
                )

            AddCardContent(
                uiState = uiState,
                cardPreview = cardPreview,
                onBackPressed = {},
                onNumberChange = { value -> uiState = uiState.copy(number = value) },
                onExpirationChange = { value -> uiState = uiState.copy(expiration = value) },
                onUserNameChange = { value -> uiState = uiState.copy(userName = value) },
                onPasswordChange = { value -> uiState = uiState.copy(password = value) },
                onDismissSheet = { uiState = uiState.copy(showCompanySheet = false) },
                onSelectCompany = {},
                onSaveClick = {},
            )
        }

        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("11 / 99").assertIsDisplayed()
        composeTestRule.onNodeWithText("JOYKIM").assertIsDisplayed()
    }
}
