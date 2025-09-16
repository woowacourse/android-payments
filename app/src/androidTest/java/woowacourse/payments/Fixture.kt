package woowacourse.payments

import androidx.compose.ui.graphics.Color
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.CardUiModel

object BankFixture {
    val BC = BankUiModel.create("BC카드", Color(0xFFF04651), R.drawable.img_bc)
    val SHINHAN = BankUiModel.create("신한카드", Color(0xFF293A94), R.drawable.img_shinhan)
    val WOORI = BankUiModel.create("우리카드", Color(0xFF2371B3), R.drawable.img_woori)
}

object CardUiModelFixture {
    val card1 =
        CardUiModel(
            bankUiModel = BankFixture.BC,
            number = "1234567887654321",
            expired = "1221",
            owner = "aaaa",
        )

    val card2 =
        CardUiModel(
            bankUiModel = BankFixture.SHINHAN,
            number = "8765432112345678",
            expired = "1122",
            owner = "bbbb",
        )
}
