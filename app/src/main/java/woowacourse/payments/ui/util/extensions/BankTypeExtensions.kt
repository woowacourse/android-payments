package woowacourse.payments.ui.util.extensions

import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
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

fun BankType.toLabel(): Int =
    when (this) {
        BankType.NONE -> R.string.NONE_bank_card_name
        BankType.BC -> R.string.BC_bank_card_name
        BankType.KB -> R.string.KB_bank_card_name
        BankType.WOORI -> R.string.WOORI_bank_card_name
        BankType.SHINHAN -> R.string.SHINHAN_bank_card_name
        BankType.KAKAO -> R.string.KAKAO_bank_card_name
        BankType.HYUNDAE -> R.string.HYUNDAE_bank_card_name
        BankType.LOTTE -> R.string.LOTTE_bank_card_name
        BankType.HANA -> R.string.HANA_bank_card_name
    }
