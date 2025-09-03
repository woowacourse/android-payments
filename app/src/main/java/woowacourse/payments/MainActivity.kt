package woowacourse.payments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { NewCardContents(this) }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NewCardContents(context: Context) {
    val cardNumber: MutableState<String> = remember { mutableStateOf("") }
    val isCardNumberError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val expirationDate: MutableState<String> = remember { mutableStateOf("") }
    val cardholderName: MutableState<String> = remember { mutableStateOf("") }
    val passcode: MutableState<String> = remember { mutableStateOf("") }
    val isPasscodeError: MutableState<Boolean> = remember { mutableStateOf(false) }

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
                        Toast
                            .makeText(context, "onSaveClick", Toast.LENGTH_SHORT)
                            .show()
                    },
                )
            },
        ) { innerPadding: PaddingValues ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
            ) {
                PaymentCard(
                    modifier =
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
                                text =
                                    if (isCardNumberError.value) {
                                        "카드 번호는 숫자 16자입니다."
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
                            cardNumber.value.isNotEmpty() && runCatching { CardNumber(cardNumber.value) }.isFailure
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
                        visualTransformation = ExpirationDateTransformation(),
                    ) { newValue: String ->
                        val numbers: String = newValue.filter(Char::isDigit)
                        expirationDate.value = numbers.substring(0..<numbers.length.coerceAtMost(4))
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
                                text =
                                    if (isPasscodeError.value) {
                                        "비밀번호는 숫자 4자입니다."
                                    } else {
                                        ""
                                    },
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                    ) { newValue: String ->
                        val numbers: String = newValue.filter(Char::isDigit)
                        passcode.value = numbers.substring(0..<numbers.length.coerceAtMost(4))
                        isPasscodeError.value =
                            passcode.value.isNotEmpty() && runCatching { Passcode(passcode.value) }.isFailure
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.new_card_title)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.new_card_back_icon_description),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.new_card_save_icon_description),
                )
            }
        },
        modifier = modifier,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun PaymentCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardInfoTextFields(
    modifier: Modifier = Modifier,
    value: String = "",
    label: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
    supportingText: @Composable () -> Unit = {},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = label,
        placeholder = placeholder,
        singleLine = true,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange,
    )
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun Preview() {
    AndroidpaymentsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding: PaddingValues ->
            Column(modifier = Modifier.fillMaxSize()) {
                NewCardTopBar(
                    onBackClick = {},
                    onSaveClick = {},
                )

                PaymentCard(
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 30.dp),
                )

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                }
            }
        }
    }
}
