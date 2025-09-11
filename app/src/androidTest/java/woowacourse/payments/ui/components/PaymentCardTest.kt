package woowacourse.payments.ui.features.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import woowacourse.payments.domain.PaymentCard as PaymentCardDomain

class PaymentCardTest {
    @get:Rule
    val compose = createComposeRule()

    private val ownerName = "CREW"
    private val cardNumber = "1234567898765432"
    private val hiddenCardNumber = "1234 - 5678 - **** - ****"

    @Test
    fun 카드_데이터가_null일_경우_카드_정보가_표시되지_않는다() {
        // given & when
        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCard()
            }
        }

        // then
        compose.onNodeWithText(ownerName).assertDoesNotExist()
        compose.onNodeWithText(cardNumber).assertDoesNotExist()
    }

    @Test
    fun 카드_데이터가_존재할_경우_가공된_카드_정보_텍스트가_올바르게_표시된다() {
        // given
        val dummyCard =
            PaymentCardDomain(
                cardNumber = CardNumber.create(cardNumber).getOrThrow(),
                expireDate = ExpireDate.create(YearMonth.now().plusMonths(1)).getOrThrow(),
                ownerName = OwnerName(ownerName),
                password = Password("1234"),
            )

        val yearMonthFormatter = DateTimeFormatter.ofPattern("MM / yy")
        val expectedExpireDate = dummyCard.expireDate.value.format(yearMonthFormatter)

        // when
        compose.setContent {
            AndroidpaymentsTheme(dynamicColor = false) {
                PaymentCard(paymentCard = dummyCard)
            }
        }

        // then
        compose.onNodeWithText(hiddenCardNumber).assertIsDisplayed()
        compose.onNodeWithText(dummyCard.ownerName.value!!).assertIsDisplayed()
        compose.onNodeWithText(expectedExpireDate).assertIsDisplayed()
    }
}
