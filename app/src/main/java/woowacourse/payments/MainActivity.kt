package woowacourse.payments

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.Expired
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var cardNumber by remember { mutableStateOf<CardNumber?>(null) }
    var expired by remember { mutableStateOf<Expired?>(null) }
    var showValidationError by remember { mutableStateOf(false) }

    AndroidpaymentsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
            ) {
                NewCardTopBar(
                    onBackClick = {},
                    onSaveClick = {
                        showValidationError = true
                        val isValid = cardNumber?.isValid == true && expired?.isValid == true

                        if (isValid) showValidationError = false
                    },
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    PaymentCard(
                        modifier = Modifier.padding(vertical = 20.dp),
                    )
                }

                CardNumberInput(
                    cardNumber = cardNumber,
                    onCardNumberChange = { cardNumber = it },
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    showValidationError = showValidationError,
                )
                ExpiredInput(
                    expired = expired,
                    onExpiredChange = { expired = it },
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    showValidationError = showValidationError,
                )
                CardOwnerInput(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp))
                PasswordInput(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text("카드 추가") },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier,
    )
}

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

@Composable
fun CardNumberInput(
    cardNumber: CardNumber?,
    onCardNumberChange: (CardNumber?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                val digits = newValue.text.filter { it.isDigit() }.take(16)
                val formatted = digits.chunked(4).joinToString("-")
                val cursorPosition = formatted.length

                textFieldValue =
                    TextFieldValue(
                        text = formatted,
                        selection = TextRange(cursorPosition),
                    )
                onCardNumberChange(CardNumber.create(digits))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "카드 번호") },
            placeholder = { Text(text = "0000 - 0000 - 0000 - 0000", color = Color.LightGray) },
            isError = showValidationError && (cardNumber?.isValid != true),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
fun ExpiredInput(
    expired: Expired?,
    onExpiredChange: (Expired?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                val digits = newValue.text.filter { it.isDigit() }.take(4)
                val formatted = digits.chunked(2).joinToString("/")
                val cursorPosition = formatted.length

                textFieldValue =
                    TextFieldValue(
                        text = formatted,
                        selection = TextRange(cursorPosition),
                    )
                onExpiredChange(Expired.create(formatted))
            },
            modifier = Modifier.fillMaxWidth(0.5f),
            label = { Text(text = "만료일") },
            placeholder = { Text(text = "MM / YY", color = Color.LightGray) },
            isError = showValidationError && (expired?.isValid != true),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
fun CardOwnerInput(modifier: Modifier = Modifier) {
    var owner by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = owner,
            onValueChange = { newValue -> owner = newValue },
            placeholder = { Text("카드에 표시된 이름을 입력하세요.", color = Color.LightGray) },
            label = { Text(text = "카드 소유자 이름(선택)") },
        )
    }
}

@Composable
fun PasswordInput(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(modifier = modifier) {
        OutlinedTextField(
            value = password,
            onValueChange = { newValue -> password = newValue },
            label = { Text(text = "비밀번호") },
            placeholder = { Text("0000", color = Color.LightGray) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (isFocused) {
                    val painter =
                        if (passwordVisible) {
                            painterResource(id = R.drawable.ic_visible)
                        } else {
                            painterResource(id = R.drawable.ic_not_visible)
                        }

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painter,
                            contentDescription = if (passwordVisible) "숨기기" else "보이기",
                        )
                    }
                }
            },
            interactionSource = interactionSource,
            modifier = Modifier.fillMaxWidth(0.5f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

// ---------- Preview ----------

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    AndroidpaymentsTheme {
        MainScreen()
    }
}

@Composable
@Preview(name = "기본 상태", showBackground = true)
@Preview(name = "다크 모드", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
fun CardTopBarPreview() {
    AndroidpaymentsTheme {
        NewCardTopBar(
            onBackClick = {},
            onSaveClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PaymentPreview() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}

@Composable
@Preview(showBackground = true)
fun CardNumberInputPreview() {
    AndroidpaymentsTheme {
//        CardNumberInput()
    }
}
