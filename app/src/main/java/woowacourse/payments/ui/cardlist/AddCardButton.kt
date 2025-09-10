package woowacourse.payments.ui.cardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.AddCardButtonBackgroundColor
import woowacourse.payments.ui.theme.AddCardButtonTextColor

@Composable
fun AddCardButton(
    modifier: Modifier = Modifier,
    onAddCard: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = AddCardButtonBackgroundColor,
                    shape = RoundedCornerShape(5.dp),
                ).clickable { onAddCard() },
    ) {
        Text(
            text = "+",
            fontSize = 34.sp,
            color = AddCardButtonTextColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardButtonPreview() {
    AddCardButton {}
}
