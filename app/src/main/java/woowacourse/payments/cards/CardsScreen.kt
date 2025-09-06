package woowacourse.payments.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
fun CardsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.card_list_title),
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 18.dp)
        )

        Text(
            text = stringResource(R.string.card_list_empty),
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 50.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardScreenPreview() {
    CardsScreen()
}

