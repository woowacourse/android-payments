package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.paymentCardUiModelSample
import woowacourse.payments.ui.theme.Gray33
import woowacourse.payments.ui.theme.YellowCB


@Composable
fun PaymentCard(paymentCardUiModel: PaymentCardUiModel?, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Gray33,
                    shape = RoundedCornerShape(5.dp),
                )
    ) {
        Column(Modifier.padding(horizontal = 14.dp)) {
            PaymentCardChip()
            if (paymentCardUiModel != null) PaymentCardContent(paymentCardUiModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    PaymentCard(paymentCardUiModelSample)
}