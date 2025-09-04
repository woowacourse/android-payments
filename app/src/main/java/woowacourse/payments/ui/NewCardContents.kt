package woowacourse.payments.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun NewCardContents(context: Context) {
    val cardNumber: MutableState<String> = remember { mutableStateOf("") }
    val expirationDate: MutableState<String> = remember { mutableStateOf("") }
    val cardholderName: MutableState<String> = remember { mutableStateOf("") }
    val passcode: MutableState<String> = remember { mutableStateOf("") }

    val isCardNumberError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isExpirationDateError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isPasscodeError: MutableState<Boolean> = remember { mutableStateOf(false) }

    fun isError(): Boolean = isCardNumberError.value || isExpirationDateError.value || isPasscodeError.value

    fun resetFields() {
        cardNumber.value = ""
        expirationDate.value = ""
        cardholderName.value = ""
        passcode.value = ""
    }

    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NewCardTopBar(
                    onBackClick = {
                        Toast
                            .makeText(context, "onBackClick", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onSaveClick = {
                        if (cardNumber.value.isEmpty()) isCardNumberError.value = true
                        if (expirationDate.value.isEmpty()) isCardNumberError.value = true
                        if (passcode.value.isEmpty()) isPasscodeError.value = true
                        if (isError()) {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.new_card_failure_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        } else {
                            resetFields()
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.new_card_success_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    },
                )
            },
        ) { innerPadding: PaddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                PaymentCard(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 30.dp),
                )

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(),
                        value = cardNumber.value,
                        label = { Text(stringResource(R.string.card_number_label)) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.card_number_placeholder),
                                color = Color(0xFFAAAAAA),
                            )
                        },
                        isError = isCardNumberError.value,
                        supportingText = {
                            Text(
                                if (isCardNumberError.value) {
                                    stringResource(R.string.card_number_error_message)
                                } else {
                                    ""
                                },
                            )
                        },
                        visualTransformation = CardNumberTransformation(),
                    ) { newValue: String ->
                        val numbers: String = newValue.filter(Char::isDigit)
                        cardNumber.value = numbers.substring(0..<numbers.length.coerceAtMost(16))
                        isCardNumberError.value =
                            runCatching { CardNumber(cardNumber.value) }.isFailure
                    }

                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        value = expirationDate.value,
                        label = { Text(stringResource(R.string.expiration_date_label)) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.expiration_date_placeholder),
                                color = Color(0xFFAAAAAA),
                            )
                        },
                        isError = isExpirationDateError.value,
                        supportingText = {
                            Text(
                                text =
                                    if (isExpirationDateError.value) {
                                        stringResource(R.string.expiration_date_error_message)
                                    } else {
                                        ""
                                    },
                            )
                        },
                        visualTransformation = ExpirationDateTransformation(),
                    ) { newValue: String ->
                        val filteredValue: String =
                            newValue.filter(Char::isDigit).take(4)
                        expirationDate.value = filteredValue
                        isExpirationDateError.value =
                            runCatching {
                                ExpirationDate(
                                    YearMonth.parse(
                                        filteredValue,
                                        DateTimeFormatter.ofPattern("MMyy"),
                                    ),
                                    YearMonth.now(),
                                )
                            }.isFailure
                    }

                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(),
                        value = cardholderName.value,
                        label = { Text(stringResource(R.string.cardholder_name_label)) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.cardholder_name_placeholder),
                                color = Color(0xFFAAAAAA),
                            )
                        },
                        supportingText = {
                            Text(
                                text = "${cardholderName.value.length} / 30",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                    ) { newValue: String ->
                        cardholderName.value =
                            newValue.substring(0..<newValue.length.coerceAtMost(30))
                    }

                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        value = passcode.value,
                        label = { Text(stringResource(R.string.passcode_label)) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.passcode_placeholder),
                                color = Color(0xFFAAAAAA),
                            )
                        },
                        isError = isPasscodeError.value,
                        supportingText = {
                            Text(
                                if (isPasscodeError.value) {
                                    stringResource(R.string.passcode_error_message)
                                } else {
                                    ""
                                },
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                    ) { newValue: String ->
                        val numbers: String = newValue.filter(Char::isDigit)
                        passcode.value = numbers.substring(0..<numbers.length.coerceAtMost(4))
                        isPasscodeError.value = runCatching { Passcode(passcode.value) }.isFailure
                    }
                }
            }
        }
    }
}
