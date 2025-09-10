package woowacourse.payments.ui.catalog.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.PaymentCards
import woowacourse.payments.ui.catalog.CardCatalogActivity.Companion.PAYMENT_CARD_UI_MODEL_KEY
import woowacourse.payments.ui.catalog.component.AddCardButton
import woowacourse.payments.ui.catalog.component.CardCatalogTopAppBar
import woowacourse.payments.ui.common.component.PaymentCardField
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.common.showToast
import woowacourse.payments.ui.mapper.toDomain
import woowacourse.payments.ui.mapper.toUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.payments.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardCatalogScreen() {
    var cardList by remember { mutableStateOf(PaymentCards(setOf())) }
    val context = LocalContext.current
    val cardCatalogLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val paymentCardUiModel =
                    activityResult.data?.getParcelableExtraCompat<PaymentCardUiModel>(
                        PAYMENT_CARD_UI_MODEL_KEY
                    )

                paymentCardUiModel?.toDomain()?.let { card ->
                    when {
                        cardList.isContain(card) -> {
                            context.showToast(context.getString(R.string.card_catalog_screen_already_registered))
                        }

                        else -> {
                            context.showToast(context.getString(R.string.card_catalog_screen_registration_card_success))
                            cardList = cardList.add(card)
                        }
                    }
                }
            } else {
                context.showToast(context.getString(R.string.card_catalog_screen_add_canceled))
            }
        }
    Scaffold(
        topBar = {
            CardCatalogTopAppBar(
                isAddButtonVisible = cardList.cards.size >= 3,
                onCardAddClick = {
                    val intent = CardRegistrationActivity.newIntent(context)
                    cardCatalogLauncher.launch(intent)
                }
            )
        },
    ) { innerPadding ->
        CardCatalogScreenContent(
            cardCatalogLauncher = cardCatalogLauncher,
            context = context,
            cardList = cardList,
            modifier = Modifier.padding(innerPadding),
            maxCardCount = 3,
        )
    }
}

@Composable
fun CardCatalogScreenContent(
    cardCatalogLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    context: Context,
    modifier: Modifier = Modifier,
    cardList: PaymentCards = PaymentCards(setOf()),
    maxCardCount: Int,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (cardList.cards.isEmpty()) {
            Text(
                text = stringResource(R.string.CARD_CATALOG_SCREEN_REGISTRATION_NEW_CARD),
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                modifier = modifier.align(Alignment.CenterHorizontally),
            )
        }

        cardList.cards.forEach { paymentCard ->
            Spacer(modifier = Modifier.height(36.dp))
            PaymentCardField(paymentCardUiModel = paymentCard.toUiModel(), modifier = Modifier)
        }

        if (cardList.cards.size < maxCardCount) {
            AddCardButton(
                onClick = {
                    val intent = CardRegistrationActivity.newIntent(context)
                    cardCatalogLauncher.launch(intent)
                },
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