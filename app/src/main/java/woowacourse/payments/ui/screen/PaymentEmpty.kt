package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.components.AddCardButton

@Composable
fun PaymentEmpty(onAddCardClick: () -> Unit) {
    Text(
        text = stringResource(R.string.payment_add_new_card_prompt),
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
    )
    Spacer(Modifier.height(32.dp))
    AddCardButton(onClick = onAddCardClick)
}
