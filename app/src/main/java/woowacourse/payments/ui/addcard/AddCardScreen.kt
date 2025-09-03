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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(modifier: Modifier) {
    Scaffold(
        topBar = {
            AddCardTopbar()
        }
    ) { padding ->
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
            AddCardContent(modifier)
        }
    }
}

@Composable
fun AddCardContent(modifier: Modifier) {
    var cardNumber: String by remember { mutableStateOf("") }
    var expireDate: String by remember { mutableStateOf("") }
    var ownerName: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            modifier = modifier
                .padding(top = 40.dp)
                .fillMaxWidth(),
            value = cardNumber,
            onValueChange = { cardNumber = it },
            singleLine = true,
            label = { Text(stringResource(R.string.addcard_card_number_label)) },
            visualTransformation = if (cardNumber.isEmpty()) PlaceholderTransformation(
                placeholder = stringResource(R.string.addcard_card_number_placeholder),
                textColor = colorResource(R.color.payments_placeholder_color)
            ) else VisualTransformation.None,
        )

        OutlinedTextField(
            modifier = modifier
                .padding(top = 18.dp)
                .fillMaxWidth(0.47f),
            singleLine = true,
            value = expireDate,
            onValueChange = { expireDate = it },
            label = { Text(stringResource(R.string.addcard_expire_date_label)) },
            visualTransformation = if (expireDate.isEmpty()) PlaceholderTransformation(
                placeholder = stringResource(R.string.addcard_expire_date_placeholder),
                textColor = colorResource(R.color.payments_placeholder_color)
            ) else VisualTransformation.None,
        )

        OutlinedTextField(
            modifier = modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
            value = ownerName,
            onValueChange = { ownerName = it },
            singleLine = true,
            label = { Text(stringResource(R.string.addcard_owner_name_label)) },
            visualTransformation = if (ownerName.isEmpty()) PlaceholderTransformation(
                placeholder = stringResource(R.string.addcard_owner_name_placeholder),
                textColor = colorResource(R.color.payments_placeholder_color)
            ) else VisualTransformation.None
        )

        OutlinedTextField(
            modifier = modifier
                .padding(top = 18.dp)
                .fillMaxWidth(0.47f),
            singleLine = true,
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.addcard_password_label)) },
            visualTransformation = if (password.isEmpty()) PlaceholderTransformation(
                placeholder = stringResource(R.string.addcard_password_placeholder),
                textColor = colorResource(R.color.payments_placeholder_color)
            ) else PasswordVisualTransformation()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen(Modifier)
    }
}