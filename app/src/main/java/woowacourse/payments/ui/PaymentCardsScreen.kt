@file:Suppress("DEPRECATION")

package woowacourse.payments.ui

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.component.PaymentCardsContent
import woowacourse.payments.ui.component.PaymentCardsTopBar
import woowacourse.payments.ui.model.EXTRA_CARD
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCardsScreen() {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.toast_card_add)
    var paymentCards by rememberSaveable { mutableStateOf(listOf<PaymentCardUiModel>()) }

    val cardAddLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val newPaymentCard = result.data?.getParcelableExtra<PaymentCardUiModel>(EXTRA_CARD)
                if (newPaymentCard != null) {
                    paymentCards = paymentCards + newPaymentCard
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

    fun launchAddCard() {
        val intent = AddPaymentCardActivity.newIntent(context)
        cardAddLauncher.launch(intent)
    }

    Scaffold(
        topBar = {
            PaymentCardsTopBar(paymentCards.size, Modifier, onAddClick = { launchAddCard() })
        },
    ) { innerPadding ->
        PaymentCardsContent(
            modifier =
                Modifier
                    .padding(innerPadding),
            paymentCards = paymentCards,
            onAddCard = { launchAddCard() },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardsPreview() {
    AndroidpaymentsTheme {
        PaymentCardsScreen()
    }
}
