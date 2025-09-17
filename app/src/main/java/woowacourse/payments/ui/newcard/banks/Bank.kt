package woowacourse.payments.ui.newcard.banks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.cards.core.mapper.asPainter
import woowacourse.payments.ui.model.ImageSource
import woowacourse.payments.ui.model.toLocalBankUiModel
import woowacourse.payments.ui.newcard.banks.BanksTestTag.BANK_CONTAINER_TAG


@Composable
fun Bank(bankType: BankType, onSelectedCard: (BankType) -> Unit, modifier: Modifier = Modifier) {
    val bankUiModel = bankType.toLocalBankUiModel() ?: return
    Column(
        modifier = modifier
            .clickable { onSelectedCard(bankType) }
            .testTag(BANK_CONTAINER_TAG),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = bankUiModel.image.asPainter(),
            contentDescription = bankUiModel.name,
            modifier = Modifier
                .size(37.dp, 37.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier.height(10.dp))
        Text(
            text = bankUiModel.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.testTag(BanksTestTag.BANK_NAME_TAG)
        )
    }
}

@Preview
@Composable
fun BankPreview() {
    Bank(BankType.BC, {})
}