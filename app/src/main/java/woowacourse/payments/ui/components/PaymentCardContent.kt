package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun PaymentCardContent(paymentCardUiModel: PaymentCardUiModel, modifier: Modifier = Modifier) {
    Spacer(modifier.height(8.dp))
    Text(
        paymentCardUiModel.maskCardNumbersFromBack(),
        fontSize = 12.sp,
        fontWeight = FontWeight.W500, letterSpacing = 0.17.em,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
    Spacer(Modifier.height(2.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = paymentCardUiModel.ownerName,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            letterSpacing = 0.10.em,
        )
        Text(
            paymentCardUiModel.formatCardExpiry(),
            fontSize = 12.sp,
            letterSpacing = 0.08.em,
            textAlign = TextAlign.Right
        )
    }
}