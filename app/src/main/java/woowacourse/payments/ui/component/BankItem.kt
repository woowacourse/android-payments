package woowacourse.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.toPresentation

@Composable
fun BankItem(
    bankType: BankType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bank = remember(bankType) { bankType.toPresentation() }

    Column(
        modifier =
            modifier
                .width(80.dp)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Image(
            painter = painterResource(id = bank.logoRes),
            contentDescription =
                stringResource(
                    id = R.string.bank_item_content_description,
                    bank.name,
                ),
            modifier = Modifier.size(48.dp),
        )

        Text(
            text = bank.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectItemPreview() {
    BankItem(
        bankType = BankType.KB,
        onClick = {},
    )
}
