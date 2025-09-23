package woowacourse.payments.ui.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.common.mapper.toNameRes

@Composable
fun BankLabel(
    bankType: BankType,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(bankType.toNameRes()),
        style = TextStyle(color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.W500),
        modifier = modifier,
    )
}
