package woowacourse.payments.ui.addcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.BankType

@Composable
fun PaymentCard(
    bank: BankType,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(bank.color),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column {
            Text(
                modifier =
                    Modifier
                        .padding(start = 14.dp, bottom = 10.dp, top = 10.dp),
                text = stringResource(id = bank.bankName),
                color = Color(0xFFFFFFFF),
                fontSize = 12.sp,
            )
            Box(
                modifier =
                    Modifier
                        .padding(start = 14.dp, bottom = 10.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
        }
    }
}
