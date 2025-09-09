package woowacourse.payments.ui.CardCatalog.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.component.PaymentCard
import woowacourse.payments.ui.theme.GrayE5

@Composable
fun CardCatalogColumn(
    cards: List<Card> = emptyList(),
    onClickAddCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        if (cards.size <= 1) {
            Text(
                text = stringResource(R.string.payments_enroll_new_card),
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        for (card in cards) {
            PaymentCard(
                card,
                Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
        if (2 > cards.size) {
            Box(
                modifier = Modifier
                    .shadow(8.dp)
                    .size(width = 208.dp, height = 124.dp)
                    .background(
                        color = GrayE5,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clickable { onClickAddCard() }
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.content_description_add_card),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCatalogColumnPreview() {
    CardCatalogColumn(onClickAddCard = {})
}
