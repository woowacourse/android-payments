package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import woowacourse.payments.ui.components.EmptyCard
import woowacourse.payments.ui.components.PaymentTopBar

@Preview
@Composable
fun PaymentScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentTopBar(modifier = Modifier.fillMaxWidth())
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.payment_add_new_card_prompt),
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
            )

            Spacer(Modifier.height(32.dp))
            EmptyCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
