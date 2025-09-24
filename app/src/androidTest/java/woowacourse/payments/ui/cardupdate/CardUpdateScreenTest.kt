package woowacourse.payments.ui.cardupdate

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.cardupdate.model.CardUpdateType
import woowacourse.payments.ui.cardupdate.model.toUiModel

@Suppress("ktlint:standard:function-naming")
class CardUpdateScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NewCardScreen(
                updateType = CardUpdateType.Add,
                companies = CardCompany.entries.map(CardCompany::toUiModel),
                onBackClick = {},
                onSaveClick = {},
            )
        }
    }

    @Test
    fun 모든_입력_필드의_라벨이_표시된다() {
        // when & then
        composeTestRule.onNodeWithText("카드 번호").assertIsDisplayed()
        composeTestRule.onNodeWithText("만료일").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 소유자 이름 (선택)").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 비밀번호").assertIsDisplayed()
    }
}
