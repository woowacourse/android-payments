package woowacourse.payments

import androidx.compose.ui.graphics.Color
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel

object CardCompanyFixture {
    val BC = CardCompanyUiModel.create("BC카드", Color(0xFFF04651), R.drawable.img_bc)
    val SHINHAN = CardCompanyUiModel.create("신한카드", Color(0xFF293A94), R.drawable.img_shinhan)
    val KAKAOBANK = CardCompanyUiModel.create("카카오뱅크", Color(0xFFFAE100), R.drawable.img_kakaobank)
    val WOORI = CardCompanyUiModel.create("우리카드", Color(0xFF2371B3), R.drawable.img_woori)
    val UNKNOWN = CardCompanyUiModel.create("", Color.Black, R.drawable.ic_not_visible)
}

object CardUiModelFixture {
    val card1 =
        CardUiModel(
            cardCompanyUiModel = CardCompanyFixture.BC,
            number = "1234567887654321",
            expired = "1221",
            owner = "aaaa",
        )

    val card2 =
        CardUiModel(
            cardCompanyUiModel = CardCompanyFixture.SHINHAN,
            number = "8765432112345678",
            expired = "1122",
            owner = "bbbb",
        )
}
