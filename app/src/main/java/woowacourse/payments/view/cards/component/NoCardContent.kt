package woowacourse.payments.view.cards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoCardContent(
    addCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "새로운 카드를 등록해주세요",
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.padding(vertical = 32.dp),
        )
        PaymentCardAdditionButton(onClick = addCard)
    }
}

@Preview(showBackground = true)
@Composable
private fun NoCardContentPreview() {
    NoCardContent(
        addCard = {},
        modifier = Modifier.fillMaxSize(),
    )
}
