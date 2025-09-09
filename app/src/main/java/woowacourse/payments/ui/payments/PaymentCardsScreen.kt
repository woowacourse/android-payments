package woowacourse.payments.ui.payments

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.common.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.payments.registration.PaymentCardRegistrationActivity
import woowacourse.payments.ui.theme.TextGray

@Composable
fun PaymentCardsScreen() {
    val paymentCards: SnapshotStateList<CardUiModel> = remember { mutableStateListOf() }
    val context = LocalContext.current

    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val card: CardUiModel? =
                    activityResult.data?.getParcelableCompat<CardUiModel>("card")
                card?.let {
                    paymentCards.add(it)
                }
            }
        }

    Scaffold(
        topBar = {
            PaymentCardsTopAppBar(
                onRegistrationClick = {
                    val intent =
                        Intent(context, PaymentCardRegistrationActivity::class.java)
                    cardAddLauncher.launch(intent)
                },
                isVisibleRegistrationButton = paymentCards.size >= 2,
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
            Spacer(modifier = Modifier.height(16.dp))

            if (paymentCards.isEmpty()) {
                RegistrationGuideText()

                Spacer(modifier = Modifier.height(16.dp))
            }

            for (card in paymentCards) {
                PaymentCard(paymentCardInformation = card)

                Spacer(modifier = Modifier.height(36.dp))
            }

            if (paymentCards.size <= 1) {
                RegistrationBox {
                    val intent = Intent(context, PaymentCardRegistrationActivity::class.java)
                    cardAddLauncher.launch(intent)
                }
            }
        }
    }
}

@Composable
private fun RegistrationGuideText() {
    Text(
        text = stringResource(R.string.payment_cards_screen_registration_guide),
        modifier =
            Modifier
                .fillMaxWidth(),
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    )
}

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
