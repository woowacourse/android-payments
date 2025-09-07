package woowacourse.payments.ui.cards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.newcard.NewCardActivity

@Composable
fun AddCardImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = colorResource(R.color.add_card_background),
                    shape = RoundedCornerShape(5.dp),
                ).clickable(true) {
                    val intent = NewCardActivity.newIntent(context)
                    context.startActivity(intent)
                },
    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddCardImagePreview() {
    AddCardImage()
}
