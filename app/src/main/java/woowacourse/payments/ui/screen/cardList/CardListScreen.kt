package woowacourse.payments.ui.screen.cardList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.component.CardListTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardListScreen(onAddClick: () -> Unit) {
    Scaffold(
        topBar = { CardListTopBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "새로운 카드를 등록해주세요",
                modifier = Modifier.padding(top = 32.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.LightGray)
                        .clickable { onAddClick() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Default.Add, contentDescription = "카드 추가 박스")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenPreview() {
    AndroidpaymentsTheme {
        CardListScreen({})
    }
}
