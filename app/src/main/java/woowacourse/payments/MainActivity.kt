package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AddCardScreen() }
    }
}

@Preview
@Composable
fun AddCardScreen() {
    AndroidpaymentsTheme {
        var number by remember { mutableStateOf(TextFieldValue("")) }
        var expiration by remember { mutableStateOf(TextFieldValue("")) }
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NewCardTopBar(
                    onBackClick = {},
                    onSaveClick = {},
                )
            },
        ) { innerPadding ->
            val fieldMax =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)

            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp)
                        .fillMaxSize(),
            ) {
                PaymentCard(
                    modifier =
                        Modifier
                            .padding(vertical = 14.dp)
                            .align(Alignment.CenterHorizontally),
                )

                CardNumberField(
                    value = number,
                    onValueChange = { number = it },
                    modifier = fieldMax.then(Modifier.padding(top = 11.dp)),
                )

                ExpirationDateField(
                    value = expiration,
                    onValueChange = { expiration = it },
                    modifier =
                        Modifier
                            .fillMaxWidth(0.5f)
                            .padding(vertical = 15.dp),
                )

                UserNameField(
                    value = userName,
                    onValueChange = { userName = it },
                    modifier = fieldMax,
                )

                PasswordField(
                    value = password,
                    onValueChange = { password = it },
                    modifier =
                        Modifier
                            .fillMaxWidth(0.5f),
                )
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
fun CardNumberField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        onValueChange = { input ->
            val digits = input.text.filter(Char::isDigit).take(16)
            val formatted = digits.chunked(4).joinToString(" - ")
            onValueChange(
                TextFieldValue(
                    text = formatted,
                    selection = TextRange(formatted.length),
                ),
            )
        },
        label = { Text("카드 번호") },
        placeholder = {
            Text(
                text = "0000 - 0000 - 0000 - 0000",
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

@Composable
fun ExpirationDateField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        onValueChange = { input ->
            val digits = input.text.filter(Char::isDigit).take(4)
            val formatted = digits.chunked(2).joinToString(" / ")
            onValueChange(
                TextFieldValue(
                    text = formatted,
                    selection = TextRange(formatted.length),
                ),
            )
        },
        label = { Text(text = "만료일") },
        placeholder = {
            Text(
                text = "MM / YY",
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

@Composable
fun UserNameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val max = 30

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { input ->
            if (input.length <= max) onValueChange(input)
        },
        label = { Text("카드 소유자 이름(선택)") },
        placeholder = { Text("카드에 표시된 이름을 입력하세요.", color = Grey40) },
        supportingText = {
            Text(
                text = "${value.length} / $max",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                textAlign = TextAlign.End,
            )
        },
        singleLine = true,
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = { input ->
            val formatted = input.filter(Char::isDigit).take(4)
            onValueChange(formatted)
        },
        label = { Text("비밀번호") },
        placeholder = {
            Text(
                text = "0000",
                color = Grey40,
            )
        },
        singleLine = true,
    )
}
