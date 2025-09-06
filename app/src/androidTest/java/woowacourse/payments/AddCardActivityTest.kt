package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("ktlint:standard:function-naming")
class AddCardActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent { AddCardScreen() }
    }

    @Test
    fun 카드를_추가하기_위한_필드들이_보인다() {
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_4자리마다_하이픈으로_자동_포맷된다() {
        val cardNumberField = composeTestRule.onNodeWithText("카드 번호")

        cardNumberField.performTextInput("1234123412341234")

        composeTestRule
            .onNodeWithText("1234 - 1234 - 1234 - 1234")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_숫자만_허용한다() {
        val cardNumberField = composeTestRule.onNodeWithText("카드 번호")

        cardNumberField.performTextInput("abcd123")

        composeTestRule
            .onNodeWithText("123")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_2자리마다_슬래시로_자동_포맷된다() {
        val expirationDateField = composeTestRule.onNodeWithText("만료일")

        expirationDateField.performTextInput("1226")

        composeTestRule
            .onNodeWithText("12 / 26")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_숫자만_허용한다() {
        val expirationDateField = composeTestRule.onNodeWithText("만료일")

        expirationDateField.performTextInput("aabb1")

        composeTestRule
            .onNodeWithText("1")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름은_정확히_30자까지_입력_되고_카운터가_30_30을_표시한다() {
        val userNameField = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
        val userName = "a".repeat(30)

        userNameField.performTextInput(userName)

        composeTestRule
            .onNodeWithText(userName)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("30 / 30")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름을_입력하면_입력값과_글자수가_표시된다() {
        val userNameField = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        userNameField.performTextInput("kimgahyun")

        composeTestRule
            .onNodeWithText("kimgahyun")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("9 / 30")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_마스킹되어_표시된다() {
        val passwordField = composeTestRule.onNodeWithText("비밀번호")
        passwordField.performTextInput("1234")

        composeTestRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }
}
