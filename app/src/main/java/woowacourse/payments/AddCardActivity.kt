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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = { NewCardTopBar(onBackClick = {}, onSaveClick = {}) },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                    ) {
                        PaymentCard(
                            modifier =
                                Modifier
                                    .padding(
                                        innerPadding,
                                    ).align(Alignment.CenterHorizontally),
                        )
                        CardNumber(
                            modifier =
                                Modifier
                                    .fillMaxWidth(),
                        )
                        EndDate(
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.5f),
                        )
                        CardOwner(
                            Modifier
                                .fillMaxWidth(),
                        )
                        PassWord(
                            Modifier
                                .fillMaxWidth(0.5f),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidpaymentsTheme {
        NewCardTopBar(onBackClick = {}, onSaveClick = {})
        PaymentCard()
        CardNumber()
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
fun CardNumber(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { cardNumber = it },
        modifier = modifier,
        placeholder = { Text("0000-0000-0000-0000") },
        label = { Text("카드 번호") },
        supportingText = {
            // 빈 Text를 넣어주면 supportingText 공간만 차지하고 내용은 보이지 않습니다.
            Text(" ")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}

@Composable
fun EndDate(modifier: Modifier = Modifier) {
    var endDate by remember { mutableStateOf("") }

    OutlinedTextField(
        value = endDate,
        onValueChange = { endDate = it },
        modifier = modifier,
        placeholder = { Text("MM / YY") },
        label = { Text("만료일") },
        supportingText = {
            // 빈 Text를 넣어주면 supportingText 공간만 차지하고 내용은 보이지 않습니다.
            Text(" ")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}

@Composable
fun CardOwner(modifier: Modifier = Modifier) {
    var cardOwner by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardOwner,
        onValueChange = { cardOwner = it },
        modifier = modifier,
        placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
        label = { Text("카드 소유자 이름(선택)") },
        singleLine = true,
        supportingText = {
            Text(
                "${cardOwner.length}/30",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}

@Composable
fun PassWord(modifier: Modifier = Modifier) {
    var passWord by remember { mutableStateOf("") }

    OutlinedTextField(
        value = passWord,
        onValueChange = { passWord = it },
        modifier = modifier,
        placeholder = { Text("0000") },
        label = { Text("비밀번호") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}
