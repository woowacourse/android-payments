package woowacourse.payments.ui.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.Grey20

@Composable
fun EmptyCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Grey20,
                    shape = RoundedCornerShape(5.dp),
                ).clickable { onClick() },
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "AddCardIcon")
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyCardPreview() {
    EmptyCard(
        onClick = {},
    )
}
