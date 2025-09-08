package woowacourse.payments.ui.cardlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddPaymentCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "카드 등록하기",
            tint = Color.DarkGray,
        )
    }
}

@Preview
@Composable
private fun AddPaymentCardPreview() {
    AddPaymentCard()
}
