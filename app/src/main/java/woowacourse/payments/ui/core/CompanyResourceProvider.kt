package woowacourse.payments.ui.core

import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.state.CardCompanyState
import woowacourse.payments.ui.theme.SignatureBcRed
import woowacourse.payments.ui.theme.SignatureHanaTeal
import woowacourse.payments.ui.theme.SignatureHyundaiBlack
import woowacourse.payments.ui.theme.SignatureKakaoYellow
import woowacourse.payments.ui.theme.SignatureKbBrown
import woowacourse.payments.ui.theme.SignatureLotteRed
import woowacourse.payments.ui.theme.SignatureShinhanBlue
import woowacourse.payments.ui.theme.SignatureWooriBlueWhite

class CompanyResourceProvider {
    fun getCompanyName(bank: CardCompanyState): Int? =
        when (bank) {
            is CardCompanyState.Selected -> {
                when (bank.company) {
                    CardCompany.BC -> R.string.bank_bc
                    CardCompany.SHINHAN -> R.string.bank_sinhan
                    CardCompany.KAKAO -> R.string.bank_kakao
                    CardCompany.HYUNDAE -> R.string.bank_hyundae
                    CardCompany.WOORI -> R.string.bank_woori
                    CardCompany.LOTTE -> R.string.bank_lotte
                    CardCompany.HANA -> R.string.bank_hana
                    CardCompany.KB -> R.string.bank_kb
                }
            }

            CardCompanyState.Empty -> null
        }

    fun getSignatureColor(cardCompany: CardCompany): Color =
        when (cardCompany) {
            CardCompany.BC -> SignatureBcRed
            CardCompany.SHINHAN -> SignatureShinhanBlue
            CardCompany.KAKAO -> SignatureKakaoYellow
            CardCompany.HYUNDAE -> SignatureHyundaiBlack
            CardCompany.WOORI -> SignatureWooriBlueWhite
            CardCompany.LOTTE -> SignatureLotteRed
            CardCompany.HANA -> SignatureHanaTeal
            CardCompany.KB -> SignatureKbBrown
        }

    fun getCompanyIcon(bank: CardCompany): Int =
        when (bank) {
            CardCompany.BC -> R.drawable.ic_bc
            CardCompany.SHINHAN -> R.drawable.ic_sinhan
            CardCompany.KAKAO -> R.drawable.ic_kakao
            CardCompany.HYUNDAE -> R.drawable.ic_hyundae
            CardCompany.WOORI -> R.drawable.ic_woori
            CardCompany.LOTTE -> R.drawable.ic_lotte
            CardCompany.HANA -> R.drawable.ic_hana
            CardCompany.KB -> R.drawable.ic_kb
        }
}
