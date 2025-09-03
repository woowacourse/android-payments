package woowacourse.payments

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding: PaddingValues ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        NewCardTopBar(
                            onBackClick = {
                                Toast
                                    .makeText(this@MainActivity, "onBackClick", Toast.LENGTH_SHORT)
                                    .show()
                            },
                            onSaveClick = {
                                Toast
                                    .makeText(this@MainActivity, "onSaveClick", Toast.LENGTH_SHORT)
                                    .show()
                            },
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
                            OutlinedTextField(
                                placeholder = "0000 - 0000 - 0000 - 0000",
                                label = "카드 번호",
                                modifier = Modifier.fillMaxWidth(),
                            ) { text: String ->
                                text.isDigitsOnly()
                            }
                            OutlinedTextField(
                                placeholder = "MM / YY",
                                label = "만료일",
                                modifier = Modifier.fillMaxWidth(0.5F),
                            ) { text: String ->
                                text.isDigitsOnly() && text.length <= 4
                            }
                            OutlinedTextField(
                                placeholder = "카드에 표시된 이름을 입력하세요.",
                                label = "카드 소유자 이름(선택)",
                                modifier = Modifier.fillMaxWidth(),
                            ) { text: String ->
                                text.length <= 30
                            }
                            OutlinedTextField(
                                placeholder = "0000",
                                label = "비밀번호",
                                applyMasking = true,
                                modifier = Modifier.fillMaxWidth(0.5F),
                            ) { text: String ->
                                text.isDigitsOnly() && text.length <= 4
                            }
                        }
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
fun OutlinedTextField(
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    applyMasking: Boolean = false,
    condition: (String) -> Boolean = { true },
) {
    val text: MutableState<String> = remember { mutableStateOf("") }

    OutlinedTextField(
        value = text.value,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        onValueChange = { newValue: String -> if (condition(newValue)) text.value = newValue },
        visualTransformation = if (applyMasking) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,
    )
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidpaymentsTheme {
        Scaffold { innerPadding: PaddingValues ->
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
                    OutlinedTextField("0000 - 0000 - 0000 - 0000", "카드 번호", Modifier.fillMaxWidth())
                    OutlinedTextField("MM / YY", "만료일")
                    OutlinedTextField(
                        "카드에 표시된 이름을 입력하세요.",
                        "카드 소유자 이름(선택)",
                        Modifier.fillMaxWidth(),
                    )
                    OutlinedTextField("0000", "비밀번호")
                }
            }
        }
    }
}
