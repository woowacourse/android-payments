package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray300

@Composable
fun EmptyCard(
    modifier: Modifier = Modifier,
    onAddCard: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.add_payment_card_guide),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Gray300,
            modifier =
                modifier
                    .padding(vertical = 32.dp),
        )
        AddCard(onAddClick = onAddCard)
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyCardPreview() {
    EmptyCard(onAddCard = {})
}
