package woowacourse.payments.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.GrayE5

@Composable
fun CardsScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.card_list_title),
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 18.dp)
        )

        when {
            cards.isEmpty() -> {
                Text(
                    text = stringResource(R.string.card_list_empty),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 50.dp)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(124.dp)
                        .width(208.dp)
                        .padding(top = 32.dp)
                        .background(color = GrayE5, shape = RoundedCornerShape(5.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.content_description_card_list_empty_),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardScreenPreview() {
    CardsScreen(emptyList())
}

