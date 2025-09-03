package woowacourse.payments.ui.features.addcard

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_OWNER_NAME
import woowacourse.payments.ui.features.addcard.components.CardExpireDateField
import woowacourse.payments.ui.features.addcard.components.CardNumberField
import woowacourse.payments.ui.features.addcard.components.CardOwnerNameField
import woowacourse.payments.ui.features.addcard.components.CardPasswordField
import woowacourse.payments.ui.features.addcard.components.NewCardTopBar
import woowacourse.payments.ui.features.addcard.components.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private val SupportingTextHeight = 20.dp
private val FormFieldSpacing = 30.dp

private fun shouldShowExpireDateError(card: Card): Boolean {
    return card.expireDate.length == Card.MAX_LENGTH_EXPIRE_DATE && !card.isValidExpireDate
}

@Composable
fun AddCardScreen(
    onNavigateBack: () -> Unit,
    onNavigateSave: () -> Unit,
) {
    var card by remember { mutableStateOf(Card()) }
    var toastMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(toastMessage) {
        toastMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            toastMessage = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onNavigateBack,
                onSaveClick = onNavigateSave,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            PaymentCard()
            Spacer(modifier = Modifier.height(40.dp))
            CardNumberField(
                value = card.cardNumber,
                onValueChange = { card = card.withCardNumber(it) },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing))
            CardExpireDateField(
                value = card.expireDate,
                onValueChange = {
                    card = card.withExpireDate(it)
                    val isErrorAfterUpdate = shouldShowExpireDateError(card)
                    if (isErrorAfterUpdate) {
                        toastMessage = "유효하지 않은 날짜입니다"
                    }
                },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.Start),
                isError = shouldShowExpireDateError(card),
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing))
            CardOwnerNameField(
                value = card.ownerName,
                onValueChange = { card = card.withOwnerName(it) },
                supportingText = {
                    Box(modifier = Modifier.height(SupportingTextHeight)) {
                        Text(
                            text = "${card.ownerName.length}/${MAX_LENGTH_OWNER_NAME}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardPasswordField(
                value = card.password,
                onValueChange = { card = card.withPassword(it) },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.Start),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen({}, {})
    }
}
