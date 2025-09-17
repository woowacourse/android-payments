package woowacourse.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.Black52

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyIcon(
    bankIcon: Int,
    bankName: String,
    cardCompany: CardCompany,
    onClick: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .size(width = 70.dp, height = 65.dp)
                .clickable { onClick(cardCompany) },
    ) {
        Image(
            painter = painterResource(id = bankIcon),
            contentDescription = bankName,
            modifier = Modifier.size(32.dp),
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = bankName,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.85).sp,
            color = Black52,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardCompanyIconPreview() {
    Column {
        CardCompanyIcon(
            bankIcon = R.drawable.ic_bc,
            bankName = "우리은행",
            cardCompany = CardCompany.BC,
            onClick = {},
        )
    }
}
