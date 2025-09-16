package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel

@Composable
fun BankTypeCard(
    bankTypeUiModel: BankTypeUiModel = BankTypeUiModel.NOT_SELECTED,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = bankTypeUiModel.color,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart)
        )

        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 14.dp, top = 15.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            text = bankTypeUiModel.displayName,
            color = Color.White
        )


    }
}


@Preview(showBackground = true)
@Composable
private fun BankTypeCardPreview() {
    BankTypeCard(BankTypeUiModel.NOT_SELECTED)
}