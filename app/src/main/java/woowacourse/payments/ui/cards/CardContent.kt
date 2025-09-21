package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.components.CardChip
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.debug.fixture.paymentCardUiModelSample

@Composable
fun CardContent(
    paymentCardUiModel: PaymentCardUiModel,
    modifier: Modifier = Modifier,
) {
    val cardContentStyle =
        MaterialTheme.typography.bodyLarge.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            color = Color.White,
        )
    Column(modifier) {
        Text(
            paymentCardUiModel.bankUiModel.name,
            color = Color.White,
            fontSize = 12.sp,
        )
        Spacer(Modifier.height(15.dp))
        CardChip()
        Spacer(Modifier.height(8.dp))
        Text(
            text = paymentCardUiModel.maskCardNumbersFromBack(),
            style = cardContentStyle,
            letterSpacing = 0.17.em,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
        Spacer(Modifier.height(2.dp))
        Row(
            Modifier.fillMaxWidth(),
        ) {
            Text(
                text = paymentCardUiModel.ownerName,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = cardContentStyle,
                letterSpacing = 0.10.em,
            )
            Text(
                text = paymentCardUiModel.formatCardExpiry(),
                style = cardContentStyle,
                letterSpacing = 0.08.em,
                textAlign = TextAlign.Right,
            )
        }
    }
}

@Preview
@Composable
fun PaymentCardContentPreview() {
    CardContent(paymentCardUiModelSample)
}
