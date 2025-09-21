package woowacourse.payments.ui.newcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.cards.core.mapper.asColor
import woowacourse.payments.ui.components.CardChip
import woowacourse.payments.ui.model.toLocalBankUiModel
import woowacourse.payments.ui.theme.Gray33

@Composable
fun CardSample(
    bankType: BankType?,
    modifier: Modifier = Modifier,
) {
    val bankUiModel = bankType?.toLocalBankUiModel()
    Box(
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = bankUiModel?.cardColor?.asColor() ?: Gray33,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            Modifier
                .padding(15.dp),
        ) {
            Text(
                bankUiModel?.name ?: "",
                color = Color.White,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(15.dp))
            CardChip()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateCardPreview() {
    CardSample(BankType.BC)
}
