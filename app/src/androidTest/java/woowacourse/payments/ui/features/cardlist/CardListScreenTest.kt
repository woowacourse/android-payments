package woowacourse.payments.ui.features.cardlist

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

val dummyPaymentCardUiModel1 =
    PaymentCardUiModel(
        1,
        CardCompanyUiModel.UNKNOWN,
        "1234 - 1234 - 1234 - 1234",
        "02 / 26",
        "CREW",
    )

val dummyPaymentCardUiModel2 =
    PaymentCardUiModel(
        2,
        CardCompanyUiModel.UNKNOWN,
        "1234 - 1234 - 1234 - 1234",
        "02 / 26",
        "CREW",
    )
private val dummyPaymentCardList1 = listOf(dummyPaymentCardUiModel1)
private val dummyPaymentCardList2 = listOf(dummyPaymentCardUiModel1, dummyPaymentCardUiModel2)

class CardListScreenTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()
    private lateinit var titleText: String
    private lateinit var addCardDescription: String

    @Before
    fun setUp() {
        titleText = compose.activity.getString(R.string.card_list_add_payment_card_title)
        addCardDescription =
            compose.activity.getString(R.string.card_list_add_payment_card_panel_description)
    }

    @Test
    fun 카드가_없으면_안내_메시지와_카드추가패널이_보인다() {
        // when
        compose.setContent {
            AndroidpaymentsTheme {
                CardListScreen(cardUiModels = emptyList(), onAddCard = {})
            }
        }

        // then
        compose.onNodeWithText(titleText).assertIsDisplayed()
        compose.onNodeWithContentDescription(addCardDescription).assertIsDisplayed()
    }

    @Test
    fun 카드가_한_개일_때_카드와_카드추가패널이_보인다() {
        // when
        compose.setContent {
            AndroidpaymentsTheme {
                CardListScreen(cardUiModels = dummyPaymentCardList1, onAddCard = {})
            }
        }

        // then
        compose.onNodeWithText(titleText).assertDoesNotExist()
        compose.onNodeWithText("CREW").assertIsDisplayed()
        compose.onNodeWithContentDescription(addCardDescription).assertIsDisplayed()
    }

    @Test
    fun 카드가_두_개_이상일_때_카드목록과_추가_탑바_버튼이_보이고_카드추가패널은_사라진다() {
        // when
        compose.setContent {
            AndroidpaymentsTheme {
                CardListScreen(cardUiModels = dummyPaymentCardList2, onAddCard = {})
            }
        }

        // then
        compose.onAllNodesWithText("CREW").assertCountEquals(2)
        compose.onNodeWithContentDescription(addCardDescription).assertDoesNotExist()
        compose.onNodeWithText("추가").assertExists()
    }
}
