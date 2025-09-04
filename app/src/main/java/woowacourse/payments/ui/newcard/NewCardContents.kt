package woowacourse.payments.ui.newcard

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun NewCardContents(
    context: Context,
    now: YearMonth = YearMonth.now(),
) {
    var cardNumber: String by remember { mutableStateOf("") }
    var expirationDate: String by remember { mutableStateOf("") }
    var cardholderName: String by remember { mutableStateOf("") }
    var passcode: String by remember { mutableStateOf("") }

    var isCardNumberError: Boolean by remember { mutableStateOf(false) }
    var isExpirationDateError: Boolean by remember { mutableStateOf(false) }
    var isPasscodeError: Boolean by remember { mutableStateOf(false) }

    fun isError(): Boolean = isCardNumberError || isExpirationDateError || isPasscodeError

    fun resetFields() {
        cardNumber = ""
        expirationDate = ""
        cardholderName = ""
        passcode = ""
    }

    fun updateCardNumber(newValue: String) {
        val filteredValue: String = newValue.filter(Char::isDigit).take(16)
        cardNumber = filteredValue
        isCardNumberError = runCatching { CardNumber(cardNumber) }.isFailure
    }

    fun updateExpirationDate(newValue: String) {
        val filteredValue: String = newValue.filter(Char::isDigit).take(4)
        expirationDate = filteredValue
        isExpirationDateError =
            runCatching {
                ExpirationDate(
                    YearMonth.parse(
                        filteredValue,
                        DateTimeFormatter.ofPattern("MMyy"),
                    ),
                    now,
                )
            }.isFailure
    }

    fun updateCardholderName(newValue: String) {
        cardholderName = newValue.take(30)
    }

    fun updatePasscode(newValue: String) {
        val filteredValue: String = newValue.filter(Char::isDigit).take(4)
        passcode = filteredValue
        isPasscodeError = runCatching { Passcode(passcode) }.isFailure
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
                        if (cardNumber.isEmpty()) isCardNumberError = true
                        if (expirationDate.isEmpty()) isExpirationDateError = true
                        if (passcode.isEmpty()) isPasscodeError = true
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
                        value = cardNumber,
                        label = stringResource(R.string.card_number_label),
                        placeholder = stringResource(R.string.card_number_placeholder),
                        isError = isCardNumberError,
                        supportingText = {
                            Text(
                                if (isCardNumberError) {
                                    stringResource(R.string.card_number_error_message)
                                } else {
                                    ""
                                },
                            )
                        },
                        visualTransformation = CardNumberTransformation(),
                    ) { newValue: String -> updateCardNumber(newValue) }

                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        value = expirationDate,
                        label = stringResource(R.string.expiration_date_label),
                        placeholder = stringResource(R.string.expiration_date_placeholder),
                        isError = isExpirationDateError,
                        supportingText = {
                            Text(
                                text =
                                    if (isExpirationDateError) {
                                        stringResource(R.string.expiration_date_error_message)
                                    } else {
                                        ""
                                    },
                            )
                        },
                        visualTransformation = ExpirationDateTransformation(),
                    ) { newValue: String -> updateExpirationDate(newValue) }

                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(),
                        value = cardholderName,
                        label = stringResource(R.string.cardholder_name_label),
                        placeholder = stringResource(R.string.cardholder_name_placeholder),
                        supportingText = {
                            Text(
                                text = "${cardholderName.length} / 30",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                    ) { newValue: String -> updateCardholderName(newValue) }

                    CardInfoTextFields(
                        modifier = Modifier.fillMaxWidth(0.5F),
                        value = passcode,
                        label = stringResource(R.string.passcode_label),
                        placeholder = stringResource(R.string.passcode_placeholder),
                        isError = isPasscodeError,
                        supportingText = {
                            Text(
                                if (isPasscodeError) {
                                    stringResource(R.string.passcode_error_message)
                                } else {
                                    ""
                                },
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                    ) { newValue: String -> updatePasscode(newValue) }
                }
            }
        }
    }
}
