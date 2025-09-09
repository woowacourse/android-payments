package woowacourse.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.component.CardWalletTopBar
import woowacourse.payments.component.EmptyCard

@Composable
fun CardWalletScreen(
) {

    Scaffold(
        topBar = {
            CardWalletTopBar(

            )
        }
    ) { innerPadding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(32.dp).fillMaxSize())
            Text("새로운 카드를 등록해주세요", fontSize = 18.sp, fontWeight = FontWeight.W700)

            Spacer(modifier = Modifier.height(32.dp).fillMaxSize())
            EmptyCard({} )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardWalletScreenPreview() {
    CardWalletScreen()
}
