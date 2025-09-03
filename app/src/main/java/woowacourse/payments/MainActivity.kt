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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(onBackClick = {}, onSaveClick = {})
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                    ) {
                        PaymentCard(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 14.dp)
                        )
                        CardNumber(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp, start = 24.dp, end = 24.dp),
                            label = "카드 번호",
                            placeHolder = "0000 - 0000 - 0000 - 0000"
                        )
                    }
                }
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
        modifier = modifier
    )
}


@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
    }
}

@Composable
fun CardNumber(
    modifier: Modifier,
    label: String,
    placeHolder: String,
) {
    var text: String by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        label = { Text(text = label) },
        onValueChange = {
            text = it
        },
        placeholder = { Text(placeHolder) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NewCardTopBar(onBackClick = {}, onSaveClick = {})
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                PaymentCard(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp)
                )
                CardNumber(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 24.dp, end = 24.dp),
                    label = "카드 번호",
                    placeHolder = "0000 - 0000 - 0000 - 0000"
                )
            }
        }
    }
}
