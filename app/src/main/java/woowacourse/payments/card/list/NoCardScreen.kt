package woowacourse.payments.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.card.component.NewCard

@Composable
fun NoCardScreen(onAddNewCardClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "새로운 카드를 등록해주세요",
            modifier = Modifier.padding(top = 32.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.padding(top = 32.dp))
        NewCard(onClick = onAddNewCardClick)
    }
}

@Preview
@Composable
fun NoCardScreenPreview() {
    NoCardScreen(onAddNewCardClick = {})
}
