package woowacourse.payments


import org.junit.jupiter.api.Test
import woowacourse.payments.model.CardInfo

class CardInfoTest {
    @Test
    fun `카드 번호에 숫자가 아닌 값을 입력받으면 해당 값을 제외한다`() {
        //given
        val cardInfo = CardInfo("123aa")

        //when
        val result = cardInfo.cardNumber

        //then
        assert(result == "123")
    }

    @Test
    fun `카드 번호에 16자 넘는 값을 입력받으면 16자에 맞춘다`() {
        //given
        val cardInfo = CardInfo("12345678901234567")

        //when
        val result = cardInfo.cardNumber.length

        //then
        assert(result <= 16)
    }

    @Test
    fun `카드 번호에 16자 넘는 값을 입력받으면 16자에 맞춘다2`() {
        //given
        val cardInfo = CardInfo("1234567890abc1234567")

        //when
        val result = cardInfo.cardNumber.length

        //then
        assert(result <= 16)
    }

    @Test
    fun `만료일에 숫자가 아닌 값을 입력받으면 해당 값을 제외한다`() {
        //given
        val cardInfo = CardInfo(
            expireDate = "123a"
        )

        //when
        val result = cardInfo.expireDate

        //then
        assert(result == "123")
    }

    @Test
    fun `만료일에 4자가 넘는 값을 입력받으면 4자에 맞춘다`() {
        //given
        val cardInfo = CardInfo(
            expireDate = "12345"
        )

        //when
        val result = cardInfo.expireDate.length

        //then
        assert(result <= 4)
    }

    @Test
    fun `만료일에 4자가 넘는 값을 입력받으면 4자에 맞춘다2`() {
        //given
        val cardInfo = CardInfo(
            expireDate = "123ab45"
        )

        //when
        val result = cardInfo.expireDate.length

        //then
        assert(result <= 4)
    }

    @Test
    fun `만료일의 월 부분에 유효하지 않은 월을 입력하였는지 판단할 수 있다`() {
        //given
        val cardInfo = CardInfo(
            expireDate = "1534"
        )

        //when
        val result = cardInfo.isExpirationDateValid()

        //then
        assert(result == false)
    }

    @Test
    fun `소유자 이름에 30자가 넘는 값이 들어오면 30자에 맞춘다`() {
        //given
        val cardInfo = CardInfo(
            ownerName = "12345678901234567890123456789012345678901234567890"
        )

        //when
        val result = cardInfo.ownerName.length

        //then
        assert(result <= 30)
    }

    @Test
    fun `비밀번호에 4자가 넘는 값이 들어오면 4자에 맞춘다`() {
        //given
        val cardInfo = CardInfo(
            password = "12345"
        )

        //when
        val result = cardInfo.password.length

        //then
        assert(result <= 4)
    }

    @Test
    fun `비밀번호에 숫자가 아닌 값이 들어오면 해당 값을 제외한다`() {
        //given
        val cardInfo = CardInfo(
            password = "1234a"
        )

        //when
        val result = cardInfo.password

        //then
        assert(result == "1234")
    }
}
