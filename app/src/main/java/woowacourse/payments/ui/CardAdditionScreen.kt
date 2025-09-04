package woowacourse.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Character.isDigit

private const val CARD_NUMBER_LENGTH_MAX: Int = 16
private const val CARD_OWNER_NAME_LENGTH_MAX: Int = 30
private const val EXPIRED_DATE_LENGTH_MAX: Int = 4
private const val PASSWORD_LENGTH_MAX: Int = 4

@Composable
fun CardAdditionScreen(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }
    var expiredDate by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = {},
                onSaveClick = {},
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp, bottom = 28.dp),
            )
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { newValue ->
                    cardNumber = newValue.filter(::isDigit).take(CARD_NUMBER_LENGTH_MAX)
                },
                maxLength = CARD_NUMBER_LENGTH_MAX,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
            )
            ExpiredDateTextField(
                value = expiredDate,
                onValueChange = { newValue ->
                    expiredDate = newValue.filter(::isDigit).take(EXPIRED_DATE_LENGTH_MAX)
                },
                maxLength = EXPIRED_DATE_LENGTH_MAX,
                modifier =
                    Modifier
                        .padding(top = 18.dp),
            )
            CardOwnerNameTextField(
                value = ownerName,
                onValueChange = { newValue ->
                    ownerName = newValue.take(CARD_OWNER_NAME_LENGTH_MAX)
                },
                maxLength = CARD_OWNER_NAME_LENGTH_MAX,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp),
            )
            PasswordTextField(
                value = password,
                onValueChange = { newValue ->
                    password = newValue.take(PASSWORD_LENGTH_MAX)
                },
            )
        }
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen()
}
