package woowacourse.payments.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.newCard.NewCardActivity
import woowacourse.payments.ui.PaymentCard
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                var list by remember { mutableStateOf(ListUiState()) }
                val context = LocalContext.current

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val newCard = result.data?.getParcelableExtra<Card>("card")
                        newCard?.let {
                            list = ListUiState(list.cards + it)
                            Toast.makeText(context, "카드가 추가되었습니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                CardListScreen(
                    cards = list.cards,
                    onAddClick = {
                        val intent = Intent(context, NewCardActivity::class.java)
                        launcher.launch(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    modifier: Modifier = Modifier,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Payments") },
        actions = actions,
        modifier = modifier,
    )
}

@Composable
fun CardListScreen(
    cards: List<Card>,
    onAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                actions = {
                    if (cards.size >= 2) {
                        TextButton(
                            onClick = onAddClick,
                            content = { Text(
                            text = "추가",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ) } )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            when (cards.size) {
                0 -> {
                    AddNewCardText()
                    AddNewCard(
                        onAddClick = onAddClick,
                    )
                }

                1 -> {
                    CardList(cards)
                    AddNewCard(
                        onAddClick = onAddClick,
                    )
                }

                else -> {
                    CardList(cards)
                }
            }
        }
    }

}

@Composable
fun CardList(
    cards: List<Card>,
) {
    cards.forEach { card: Card -> PaymentCard(card = card) }
}

@Composable
fun AddNewCard(
    onAddClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .height(124.dp)
            .width(208.dp)
            .clickable(onClick = onAddClick)
            .background(color = Color(0xFFE5E5E5)),
        content = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "새로운 카드 추가",
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        })
}

@Composable
fun AddNewCardText() {
    Text(text = "새로운 카드를 등록해주세요", fontSize = 18.sp, fontWeight = FontWeight.Bold)
}

@Preview(name = "카드가 없을 때")
@Composable
private fun EmptyCardListPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                CardListScreen(
                    emptyList(),
                    onAddClick = {}
                )
            }
        }
    }
}

@Preview(name = "카드가 한 개일 때")
@Composable
private fun AddOneCardListPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                CardListScreen(
                    listOf(
                        Card(
                            "0000000000000000",
                            "0925",
                            "1234",
                            "PARK JIWON"
                        )
                    ),
                    onAddClick = {}
                )
            }
        }
    }
}

@Preview(name = "카드가 여러 개일 때")
@Composable
private fun AddTwoOrMoreCardListPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                CardListScreen(
                    listOf(
                        Card(
                            "0000000000000000",
                            "1225",
                            "1234",
                            "PARK JIWON"
                        ),
                        Card(
                            "1234123412341234",
                            "0999",
                            "9999",
                            "TOMATO BASIL ADE"
                        ),
                    ),
                    onAddClick = {}
                )
            }
        }
    }
}