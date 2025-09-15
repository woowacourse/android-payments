package woowacourse.payments.newCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardCompanyItem(
    cardCompanyUiModel: CardCompanyUiModel,
    onClick: (CardCompanyUiModel) -> Unit
) {
    Box(modifier = Modifier
        .height(65.dp)
        .width(80.dp).clickable { onClick(cardCompanyUiModel)},
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = cardCompanyUiModel.iconRes),
                tint = Color.Unspecified,
                contentDescription = cardCompanyUiModel.displayName,
                modifier = Modifier.size(37.dp),
            )
            Text(text = cardCompanyUiModel.displayName,
                fontSize = 16.sp,
                color = Color(0xFF525252)
            )
        }
    }
}