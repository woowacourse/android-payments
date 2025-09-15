package woowacourse.payments.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.GrayE5

@Composable
fun PaymentCreateCard(
    onAddCardCategoryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = GrayE5,
                    shape = RoundedCornerShape(5.dp),
                ).clickable(onClick = onAddCardCategoryClick),
    ) {
        Image(painter = painterResource(R.drawable.ic_create_card), "카드 추가 버튼 입니다")
    }
}

@Preview
@Composable
fun PaymentCreateCardPreview() {
    PaymentCreateCard({})
}
