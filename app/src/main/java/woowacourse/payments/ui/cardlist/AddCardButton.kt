package woowacourse.payments.ui.cardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

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
                    color = Color(0xFFE5E5E5),
                    shape = RoundedCornerShape(5.dp),
                ).clickable { onAddCard() }
                .testTag("카드 추가 버튼"),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.Gray,
            contentDescription = stringResource(R.string.add_card_button_description),
        )
    }
}

@Preview(showBackground = true, name = "카드 추가 버튼")
@Composable
private fun AddCardButtonPreview() {
    AddCardButton {}
}
