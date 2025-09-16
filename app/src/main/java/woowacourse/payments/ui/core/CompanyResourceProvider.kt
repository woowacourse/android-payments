package woowacourse.payments.ui.core

import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.Banks
import woowacourse.payments.ui.state.BankState
import woowacourse.payments.ui.theme.SignatureBcRed
import woowacourse.payments.ui.theme.SignatureHanaTeal
import woowacourse.payments.ui.theme.SignatureHyundaiBlack
import woowacourse.payments.ui.theme.SignatureKakaoYellow
import woowacourse.payments.ui.theme.SignatureKbBrown
import woowacourse.payments.ui.theme.SignatureLotteRed
import woowacourse.payments.ui.theme.SignatureShinhanBlue
import woowacourse.payments.ui.theme.SignatureWooriBlueWhite

class CompanyResourceProvider {
    fun getCompanyName(bank: BankState): Int? =
        when (bank) {
            is BankState.Bank -> {
                when (bank.company) {
                    Banks.BC -> R.string.bank_bc
                    Banks.SHINHAN -> R.string.bank_sinhan
                    Banks.KAKAO -> R.string.bank_kakao
                    Banks.HYUNDAE -> R.string.bank_hyundae
                    Banks.WOORI -> R.string.bank_woori
                    Banks.LOTTE -> R.string.bank_lotte
                    Banks.HANA -> R.string.bank_hana
                    Banks.KB -> R.string.bank_kb
                }
            }

            BankState.Empty -> null
        }

    fun getSignatureColor(banks: Banks): Color =
        when (banks) {
            Banks.BC -> SignatureBcRed
            Banks.SHINHAN -> SignatureShinhanBlue
            Banks.KAKAO -> SignatureKakaoYellow
            Banks.HYUNDAE -> SignatureHyundaiBlack
            Banks.WOORI -> SignatureWooriBlueWhite
            Banks.LOTTE -> SignatureLotteRed
            Banks.HANA -> SignatureHanaTeal
            Banks.KB -> SignatureKbBrown
        }

    fun getCompanyIcon(bank: Banks): Int =
        when (bank) {
            Banks.BC -> R.drawable.ic_bc
            Banks.SHINHAN -> R.drawable.ic_sinhan
            Banks.KAKAO -> R.drawable.ic_kakao
            Banks.HYUNDAE -> R.drawable.ic_hyundae
            Banks.WOORI -> R.drawable.ic_woori
            Banks.LOTTE -> R.drawable.ic_lotte
            Banks.HANA -> R.drawable.ic_hana
            Banks.KB -> R.drawable.ic_kb
        }
}
