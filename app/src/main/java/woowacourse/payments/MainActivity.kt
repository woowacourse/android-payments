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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                            .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    NewCardInfoFields(context)
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
fun NewCardInfoFields(context: Context) {
    val cardNumber: MutableState<String> = remember { mutableStateOf("") }
    OutlinedTextField(
        value = cardNumber.value,
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = { Text(stringResource(R.string.card_number_placeholder)) },
        onValueChange = { newValue: String ->
            val numbers: String = newValue.filter(Char::isDigit)
            cardNumber.value = numbers.substring(0..<numbers.length.coerceAtMost(16))
        },
        visualTransformation = CardNumberTransformation(),
        modifier = Modifier.fillMaxWidth(),
    )

    val expirationDate: MutableState<String> = remember { mutableStateOf(String()) }
    OutlinedTextField(
        value = expirationDate.value,
        label = { Text(stringResource(R.string.expiration_date_label)) },
        placeholder = { Text(stringResource(R.string.expiration_date_placeholder)) },
        onValueChange = { newValue: String ->
            val numbers: String = newValue.filter(Char::isDigit)
            expirationDate.value = numbers.substring(0..<numbers.length.coerceAtMost(4))
        },
        visualTransformation = ExpirationDateTransformation(),
        modifier = Modifier.fillMaxWidth(0.5F),
    )

    val cardholderName: MutableState<String> = remember { mutableStateOf("") }
    OutlinedTextField(
        value = cardholderName.value,
        label = { Text(stringResource(R.string.cardholder_name_label)) },
        placeholder = { Text(stringResource(R.string.cardholder_name_placeholder)) },
        onValueChange = { newValue: String ->
            if (newValue.length <= 30) {
                cardholderName.value = newValue
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )

    val passcode: MutableState<String> = remember { mutableStateOf("") }
    OutlinedTextField(
        value = passcode.value,
        label = { Text(stringResource(R.string.passcode_label)) },
        placeholder = { Text(stringResource(R.string.passcode_placeholder)) },
        onValueChange = { newValue: String ->
            val numbers: String = newValue.filter(Char::isDigit)
            if (numbers.length <= 4) {
                passcode.value = numbers
            }
        },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth(0.5F),
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
