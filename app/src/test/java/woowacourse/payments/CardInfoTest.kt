package woowacourse.payments

import org.junit.jupiter.api.Test
import woowacourse.payments.model.CardInfo
import woowacourse.payments.model.CardVendor

class CardInfoTest {
    @Test
    fun `카드 번호에 숫자가 아닌 값을 입력받으면 해당 값을 제외한다`() {
        // given
        val rawCardNumber = "123aa"

        // when
        val result = CardInfo.formatCardNumber(rawCardNumber)

        // then
        assert(result == "123")
    }

    @Test
    fun `카드 번호에 16자 넘는 값을 입력받으면 16자에 맞춘다`() {
        // given
        val rawCardNumber = "12345678901234567"

        // when
        val result = CardInfo.formatCardNumber(rawCardNumber).length

        // then
        assert(result <= 16)
    }

    @Test
    fun `카드 번호에 16자 넘는 값을 입력받으면 16자에 맞춘다2`() {
        // given
        val rawCardNuber = "1234567890abc1234567"

        // when
        val result = CardInfo.formatCardNumber(rawCardNuber).length

        // then
        assert(result <= 16)
    }

    @Test
    fun `만료일에 숫자가 아닌 값을 입력받으면 해당 값을 제외한다`() {
        // given
        val rawExpiredDate = "123a"

        // when
        val result = CardInfo.formatExpireDate(rawExpiredDate)

        // then
        assert(result == "123")
    }

    @Test
    fun `만료일에 4자가 넘는 값을 입력받으면 4자에 맞춘다`() {
        // given
        val rawExpiredDate = "123445"

        // when
        val result = CardInfo.formatExpireDate(rawExpiredDate).length

        // then
        assert(result <= 4)
    }

    @Test
    fun `만료일에 4자가 넘는 값을 입력받으면 4자에 맞춘다2`() {
        // given
        val rawExpiredDate = "123ab45"

        // when
        val result = CardInfo.formatExpireDate(rawExpiredDate).length

        // then
        assert(result <= 4)
    }

    @Test
    fun `만료일의 월 부분에 유효하지 않은 월을 입력하였는지 판단할 수 있다`() {
        // given
        val rawExpiredDate = "1534"

        // when
        val result = CardInfo.checkIsValidMonth(rawExpiredDate)

        // then
        assert(result == false)
    }

    @Test
    fun `소유자 이름에 30자가 넘는 값이 들어오면 30자에 맞춘다`() {
        // given
        val rawOwnerName = "12345678901234567890123456789012345678901234567890"

        // when
        val result = CardInfo.formatOwnerName(rawOwnerName).length

        // then
        assert(result <= 30)
    }

    @Test
    fun `비밀번호에 4자가 넘는 값이 들어오면 4자에 맞춘다`() {
        // given
        val rawPassword = "12345"

        // when
        val result = CardInfo.formatPassword(rawPassword).length

        // then
        assert(result <= 4)
    }

    @Test
    fun `비밀번호에 숫자가 아닌 값이 들어오면 해당 값을 제외한다`() {
        // given
        val rawPassword = "1234a"

        // when
        val result = CardInfo.formatPassword(rawPassword)

        // then
        assert(result == "1234")
    }

    @Test
    fun `유효하지 않은 값을 입력받으면 객체를 생성하지 않는다`() {
        // given
        val rawPassword = "123"

        // when
        val result =
            CardInfo.createOrNull(
                cardNumber = "1234123412341234",
                password = rawPassword,
                ownerName = "홍길동",
                expireDate = "1225",
            )

        // then
        assert(result == null)
    }

    @Test
    fun `유효한 값을 입력받으면 객체를 생성한다`() {
        // given
        val rawPassword = "1234"

        // when
        val result =
            CardInfo.createOrNull(
                cardNumber = "1234123412341234",
                password = rawPassword,
                ownerName = "홍길동",
                expireDate = "1225",
                vendor = CardVendor.BCCard
            )

        // then
        assert(result is CardInfo)
    }
}
