package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.model.CardInfo
import woowacourse.payments.ui.addcard.util.CardNumberTransformation
import woowacourse.payments.ui.addcard.util.ExpirationDateTransformation
import woowacourse.payments.ui.addcard.util.PlaceholderTransformation
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(modifier: Modifier) {
    Scaffold(
        topBar = {
            AddCardTopbar()
        }
    ) { padding ->
        var cardInfo by remember { mutableStateOf(CardInfoUiState()) }
        Column(
            modifier = modifier
                .padding(padding)
                .padding(top = 14.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier)
            AddCardContent(modifier, cardInfo)
        }
    }
}

@Composable
fun AddCardContent(
    modifier: Modifier,
    cardInfo: CardInfoUiState,
) {
    Column {
        CardNumberTextField(modifier, cardInfo)
        ExpireDateTextField(modifier, cardInfo)
        OwnerNameTextField(modifier, cardInfo)
        PasswordTextField(modifier, cardInfo)
    }
}

@Composable
fun CardNumberTextField(
    modifier: Modifier,
    cardInfo: CardInfoUiState,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(top = 40.dp)
            .fillMaxWidth(),
        value = cardInfo.cardNumber,
        onValueChange = {
            cardInfo.onValueChanged(cardNumber = it)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.addcard_card_number_label)) },
        visualTransformation = if (cardInfo.cardNumber.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_card_number_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else CardNumberTransformation(),
    )
}

@Composable
fun ExpireDateTextField(
    modifier: Modifier,
    cardInfo: CardInfoUiState,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(top = 18.dp)
            .fillMaxWidth(0.47f),
        singleLine = true,
        value = cardInfo.expireDate,
        onValueChange = {
            cardInfo.onValueChanged(expireDate = it)
        },
        isError = !cardInfo.isExpirationDateValid,
        label = { Text(stringResource(R.string.addcard_expire_date_label)) },
        supportingText = {
            if (!cardInfo.isExpirationDateValid) {
                Text("유효하지 않은 날짜입니다")
            }
        },
        visualTransformation = if (cardInfo.expireDate.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_expire_date_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else ExpirationDateTransformation(),
    )
}

@Composable
fun OwnerNameTextField(
    modifier: Modifier,
    cardInfo: CardInfoUiState,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(top = 18.dp)
            .fillMaxWidth(),
        value = cardInfo.ownerName,
        onValueChange = {
            cardInfo.onValueChanged(ownerName = it)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.addcard_owner_name_label)) },
        supportingText = {
            Text(
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                text = "${cardInfo.ownerName.length}/${CardInfoUiState.OWNER_NAME_MAX_SIZE}"
            )
        },
        visualTransformation = if (cardInfo.ownerName.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_owner_name_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else VisualTransformation.None
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier,
    cardInfo: CardInfoUiState,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(0.47f),
        singleLine = true,
        value = cardInfo.password,
        onValueChange = {
            cardInfo.onValueChanged(password = it)
        },
        label = { Text(stringResource(R.string.addcard_password_label)) },
        visualTransformation = if (cardInfo.password.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_password_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else PasswordVisualTransformation()
    )
}


@Preview(showBackground = true)
@Composable
private fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen(Modifier)
    }
}