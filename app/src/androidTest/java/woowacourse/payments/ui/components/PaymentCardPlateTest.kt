package woowacourse.payments.ui.features.addcard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextLayoutResult
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.components.PaymentCardPlate
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentCardPlateTest {
    @get:Rule
    val compose = createComposeRule()

    val dummyPaymentCardUiModel =
        PaymentCardUiModel(
            CardCompanyUiModel.UNKNOWN,
            "1234 - 1234 - 1234 - 1234",
            "02 / 26",
            "CREW",
        )

    val dummyPaymentKakaoCardUiModel =
        PaymentCardUiModel(
            CardCompanyUiModel.KAKAO,
            "1234 - 1234 - 1234 - 1234",
            "02 / 26",
            "CREW",
        )

    fun hasTextColor(expected: Color) =
        SemanticsMatcher("Text color == $expected") { node ->
            val results = mutableListOf<TextLayoutResult>()
            val action = node.config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action
            action?.invoke(results) == true &&
                results
                    .firstOrNull()
                    ?.layoutInput
                    ?.style
                    ?.color == expected
        }

    @Test
    fun 카드_UI_모델이_주어지지_않는_경우_카드_정보가_표시되지_않는다() {
        // given & when
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCardPlate()
            }
        }

        // then
        compose
            .onNodeWithText(context.getString(dummyPaymentKakaoCardUiModel.cardCompanyUiModel.companyNameResId))
            .assertDoesNotExist()
        compose
            .onNodeWithText(dummyPaymentKakaoCardUiModel.formattedCardNumber)
            .assertDoesNotExist()
        compose
            .onNodeWithText(dummyPaymentKakaoCardUiModel.formattedExpireDate)
            .assertDoesNotExist()
        compose.onNodeWithText(dummyPaymentKakaoCardUiModel.ownerName).assertDoesNotExist()
    }

    @Test
    fun 카드_UI_모델이_주어지지_않는_경우_카드_정보_텍스트가_올바르게_표시된다() {
        // given & when
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCardPlate(paymentCardUiModel = dummyPaymentKakaoCardUiModel)
            }
        }

        // then
        compose
            .onNodeWithText(context.getString(dummyPaymentKakaoCardUiModel.cardCompanyUiModel.companyNameResId))
            .assertIsDisplayed()
        compose.onNodeWithText(dummyPaymentKakaoCardUiModel.formattedCardNumber).assertIsDisplayed()
        compose.onNodeWithText(dummyPaymentKakaoCardUiModel.formattedExpireDate).assertIsDisplayed()
        compose.onNodeWithText(dummyPaymentKakaoCardUiModel.ownerName).assertIsDisplayed()
    }

    @Test
    fun 카드_정보_표시_텍스트의_글자색은_카드_UI_모델의_값이_사용된다() {
        // given
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val cardModel = dummyPaymentKakaoCardUiModel
        val expectedColor = cardModel.cardCompanyUiModel.textColor

        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCardPlate(paymentCardUiModel = cardModel)
            }
        }

        // then
        compose
            .onNodeWithText(context.getString(cardModel.cardCompanyUiModel.companyNameResId))
            .assertExists()
            .assert(hasTextColor(expectedColor))

        compose
            .onNodeWithText(cardModel.formattedCardNumber)
            .assertExists()
            .assert(hasTextColor(expectedColor))

        compose
            .onNodeWithText(cardModel.formattedExpireDate)
            .assertExists()
            .assert(hasTextColor(expectedColor))

        compose
            .onNodeWithText(cardModel.ownerName)
            .assertExists()
            .assert(hasTextColor(expectedColor))
    }
}
