package woowacourse.payments.ui.features.addcard

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.features.addcard.components.CardExpireDateField
import woowacourse.payments.ui.features.addcard.components.CardNumberField
import woowacourse.payments.ui.features.addcard.components.CardOwnerNameField
import woowacourse.payments.ui.features.addcard.components.CardPasswordField
import woowacourse.payments.ui.features.addcard.components.NewCardTopBar
import woowacourse.payments.ui.mapper.CardCreationResult
import woowacourse.payments.ui.mapper.CardMapper.getExpireDateStatus
import woowacourse.payments.ui.mapper.CardMapper.toDomainCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private val SupportingTextHeight = 20.dp
private val FormFieldSpacing = 30.dp

private fun showToast(
    context: Context,
    @StringRes
    message: Int,
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun AddCardScreen(
    onNavigateBack: () -> Unit,
    onNavigateSave: (PaymentCard) -> Unit,
) {
    var cardUiState by remember { mutableStateOf(CardUiState()) }
    val expireDateStatus by remember(cardUiState.expireDate) {
        derivedStateOf { getExpireDateStatus(cardUiState.expireDate) }
    }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onNavigateBack,
                onSaveClick = {
                    val cardDomainResult = cardUiState.toDomainCard()
                    when (cardDomainResult) {
                        CardCreationResult.InvalidCardNumber ->
                            showToast(
                                context,
                                R.string.card_list_incomplete_card_number_field_alert,
                            )

                        is CardCreationResult.InvalidExpireDate ->
                            showToast(
                                context,
                                R.string.card_list_incomplete_expire_date_field_alert,
                            )

                        CardCreationResult.InvalidPassword ->
                            showToast(
                                context,
                                R.string.card_list_incomplete_card_password_field_alert,
                            )

                        is CardCreationResult.Success -> onNavigateSave(cardDomainResult.paymentCard)
                        CardCreationResult.InvalidOwnerName -> return@NewCardTopBar
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            PaymentCard(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(40.dp))
            CardNumberField(
                value = cardUiState.cardNumber,
                onValueChange = { cardUiState = cardUiState.copy(cardNumber = it) },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardExpireDateField(
                value = cardUiState.expireDate,
                onValueChange = { cardUiState = cardUiState.copy(expireDate = it) },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f),
                expireDateStatus = expireDateStatus,
                supportingTextHeight = SupportingTextHeight,
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardOwnerNameField(
                value = cardUiState.ownerName,
                onValueChange = { cardUiState = cardUiState.copy(ownerName = it) },
            )
            Spacer(modifier = Modifier.height(FormFieldSpacing - SupportingTextHeight))
            CardPasswordField(
                value = cardUiState.password,
                onValueChange = { cardUiState = cardUiState.copy(password = it) },
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f),
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
