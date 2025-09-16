package woowacourse.payments.ui.card.register.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.BankType

@Composable
fun BankItem(
    bank: Bank,
    onClick: (Bank) -> Unit,
) {
    fun getIconResId(bankType: BankType): Int =
        when (bankType) {
            BankType.BC -> R.drawable.ic_bank_bc
            BankType.SHINHAN -> R.drawable.ic_bank_shinhan
            BankType.KAKAO -> R.drawable.ic_bank_kakao
            BankType.HYUNDAI -> R.drawable.ic_bank_hyundai
            BankType.WOORI -> R.drawable.ic_bank_woori
            BankType.LOTTE -> R.drawable.ic_bank_lotte
            BankType.HANA -> R.drawable.ic_bank_hana
            BankType.KB -> R.drawable.ic_bank_kb
            BankType.NOT_SELECTED -> R.drawable.ic_launcher_foreground
        }

    Column(
        modifier = Modifier.clickable { onClick(bank) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier =
                Modifier
                    .size(36.dp)
                    .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(id = getIconResId(bank.bankType)),
                contentDescription = bank.name,
            )
        }
        Text(
            text = bank.name,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun BankItemPreview() {
    val bank = Bank(bankType = BankType.BC, name = "BC카드")
    BankItem(bank = bank, onClick = {})
}
