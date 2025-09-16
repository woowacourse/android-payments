package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.newcard.CardStateHolder
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel
import woowacourse.payments.ui.theme.Gray52

@Composable
fun BankLogo(
    bankTypeUiModel: BankTypeUiModel,
    selectedBank: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(69.dp)
            .height(65.dp)
            .clickable { selectedBank() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            bankTypeUiModel.logo?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = stringResource(R.string.content_description_card_logo),
                    modifier = modifier
                        .size(37.dp)
                        .clip(CircleShape)
                )
            }
            Text(
                text = bankTypeUiModel.displayName,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                letterSpacing = -0.085.em,
                color = Gray52,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BankLogoPreview() {
    BankLogo(bankTypeUiModel = BankTypeUiModel.KAKAO, {})
}