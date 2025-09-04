package woowacourse.payments.ui.features.addcard

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_OWNER_NAME
import woowacourse.payments.ui.features.addcard.CardMapper.getExpireDateStatus
import woowacourse.payments.ui.features.addcard.components.CardExpireDateField
import woowacourse.payments.ui.features.addcard.components.CardNumberField
import woowacourse.payments.ui.features.addcard.components.CardOwnerNameField
import woowacourse.payments.ui.features.addcard.components.CardPasswordField
import woowacourse.payments.ui.features.addcard.components.NewCardTopBar
import woowacourse.payments.ui.features.addcard.components.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private val SupportingTextHeight = 20.dp
private val FormFieldSpacing = 30.dp

@Composable
fun AddCardScreen(
    onNavigateBack: () -> Unit,
    onNavigateSave: () -> Unit,
) {
    var cardUiState by remember { mutableStateOf(CardUiState()) }
    val expireDateStatus by remember(cardUiState.expireDate) {
        derivedStateOf { getExpireDateStatus(cardUiState.expireDate) }
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
                value = cardUiState.cardNumber,
                onValueChange = { cardUiState = cardUiState.withCardNumber(it) },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing))
            CardExpireDateField(
                value = cardUiState.expireDate,
                onValueChange = { cardUiState = cardUiState.withExpireDate(it) },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.Start),
                expireDateStatus = expireDateStatus,
                supportingTextHeight = SupportingTextHeight,
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardOwnerNameField(
                value = cardUiState.ownerName,
                onValueChange = { cardUiState = cardUiState.withOwnerName(it) },
                supportingText = {
                    Box(modifier = Modifier.height(SupportingTextHeight)) {
                        Text(
                            text = "${cardUiState.ownerName.length}/${MAX_LENGTH_OWNER_NAME}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardPasswordField(
                value = cardUiState.password,
                onValueChange = { cardUiState = cardUiState.withPassword(it) },
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
