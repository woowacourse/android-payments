package woowacourse.payments.ui.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.TextGray

@Composable
fun PaymentCardsScreen() {
    Scaffold(
        topBar = {
            PaymentCardsTopAppBar(
                onRegistrationClick = {
                    val intent =
                        Intent(context, PaymentCardRegistrationActivity::class.java)
                    cardAddLauncher.launch(intent)
                },
                isVisibleRegistrationButton = false,
                modifier = Modifier,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.payment_cards_screen_registration_guide),
                modifier =
                    Modifier
                        .padding(vertical = 32.dp)
                        .fillMaxWidth(),
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )

@Composable
private fun RegistrationBox(onClickRegistration: () -> Unit) {
    Box(
        modifier =
            Modifier
                .size(width = 208.dp, height = 124.dp)
                .background(Color.LightGray)
                .clickable { onClickRegistration() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.payment_cards_screen_registration_symbol),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = TextGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardsScreenPreview() {
    PaymentCardsScreen()
}
