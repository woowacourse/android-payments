package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.model.IssuingBank

@Composable
fun BankInfo(
    onBankSelect: (IssuingBank) -> Unit,
    modifier: Modifier = Modifier,
    issuingBank: IssuingBank = IssuingBank.NOT_SELECTED,
) {
    val context = LocalContext.current

    Column(
        modifier =
            modifier.clickable(onClick = { onBankSelect(issuingBank) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(issuingBank.getIconResId()),
            contentDescription = stringResource(R.string.card_addition_issuing_bank_icon_description),
            modifier =
                Modifier
                    .clip(CircleShape)
                    .size(36.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(issuingBank.getNameResId()),
            modifier =
                Modifier.semantics {
                    contentDescription =
                        context.getString(R.string.card_addition_issuing_bank_name_description)
                },
            fontSize = 16.sp,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun BankInfoPreview() {
    BankInfo(
        onBankSelect = {},
        issuingBank = IssuingBank.BC,
    )
}
