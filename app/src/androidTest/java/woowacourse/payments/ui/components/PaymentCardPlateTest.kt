package woowacourse.payments.ui.features.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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

    @Test
    fun 카드_UI_모델이_주어지지_않는_경우_카드_정보가_표시되지_않는다() {
        // given & when
        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCardPlate()
            }
        }

        // then
        compose.onNodeWithText(dummyPaymentCardUiModel.formattedCardNumber).assertDoesNotExist()
        compose.onNodeWithText(dummyPaymentCardUiModel.formattedExpireDate).assertDoesNotExist()
        compose.onNodeWithText(dummyPaymentCardUiModel.ownerName).assertDoesNotExist()
    }

    @Test
    fun 카드_UI_모델이_주어지지_않는_경우_카드_정보_텍스트가_올바르게_표시된다() {
        // given & when
        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCardPlate(paymentCardUiModel = dummyPaymentCardUiModel)
            }
        }

        // then
        compose.onNodeWithText(dummyPaymentCardUiModel.formattedCardNumber).assertIsDisplayed()
        compose.onNodeWithText(dummyPaymentCardUiModel.formattedExpireDate).assertIsDisplayed()
        compose.onNodeWithText(dummyPaymentCardUiModel.ownerName).assertIsDisplayed()
    }
}
