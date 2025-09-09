package woowacourse.payments.ui.catalog.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.catalog.component.AddCardButton
import woowacourse.payments.ui.catalog.component.CardCatalogTopAppBar
import woowacourse.payments.ui.payments.component.PaymentCardField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardCatalogScreen() {
    val cardList = remember { mutableStateListOf<PaymentCard>() }

    Scaffold(
        topBar = { CardCatalogTopAppBar {} },
    ) { innerPadding ->
        CardCatalogScreenContent(cardList = cardList, modifier = Modifier.padding(innerPadding), maxCardCount = 3)
    }
}

@Composable
fun CardCatalogScreenContent(
    modifier: Modifier = Modifier,
    cardList: List<PaymentCard> = emptyList<PaymentCard>(),
    maxCardCount: Int,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (cardList.isEmpty()) {
            Text(
                text = stringResource(R.string.CARD_CATALOG_SCREEN_REGISTRATION_NEW_CARD),
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                modifier = modifier.align(Alignment.CenterHorizontally),
            )
        }

        cardList.forEach { paymentCard->
            Spacer(modifier = Modifier.height(36.dp))
            PaymentCardField(paymentCard = paymentCard, modifier = Modifier)
        }

        if (cardList.size < maxCardCount){
            AddCardButton(
                onClick = {},
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardCatalogScreenPreView() {
    AndroidpaymentsTheme {
        CardCatalogScreen()
    }
}