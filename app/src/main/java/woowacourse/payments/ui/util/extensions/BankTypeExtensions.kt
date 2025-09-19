package woowacourse.payments.ui.util.extensions

import androidx.compose.ui.graphics.Color
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.theme.BCBankColor
import woowacourse.payments.ui.theme.DefaultCardColor
import woowacourse.payments.ui.theme.HANABankColor
import woowacourse.payments.ui.theme.HYUNDAEBankColor
import woowacourse.payments.ui.theme.KAKAOBankColor
import woowacourse.payments.ui.theme.KBBankColor
import woowacourse.payments.ui.theme.LOTTEBankColor
import woowacourse.payments.ui.theme.SHINHANBankColor
import woowacourse.payments.ui.theme.WOORIBankColor

fun BankType.toColor(): Color =
    when (this) {
        BankType.NONE -> DefaultCardColor
        BankType.BC -> BCBankColor
        BankType.KB -> KBBankColor
        BankType.WOORI -> WOORIBankColor
        BankType.SHINHAN -> SHINHANBankColor
        BankType.KAKAO -> KAKAOBankColor
        BankType.HYUNDAE -> HYUNDAEBankColor
        BankType.LOTTE -> LOTTEBankColor
        BankType.HANA -> HANABankColor
    }
