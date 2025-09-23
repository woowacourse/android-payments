package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.Gray52

@Composable
fun SelectableCardCompanyLogo(
    cardCompany: CardCompanyUiModel.SelectCardCompany,
    selectedBank: (CardCompanyUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(69.dp)
            .height(65.dp)
            .clickable { selectedBank(cardCompany) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            cardCompany.logo?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = stringResource(R.string.content_description_card_logo),
                    modifier = modifier
                        .size(37.dp)
                        .clip(CircleShape)
                )
            }
            Text(
                text = cardCompany.displayName.let { stringResource(it) },
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                letterSpacing = -0.085.em,
                color = Gray52,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BankLogoPreview(
    @PreviewParameter(CardCompanyLogoPreviewParameterProvider::class) cardCompany: CardCompanyUiModel.SelectCardCompany,
) {
    SelectableCardCompanyLogo(cardCompany = cardCompany, {})
}

class CardCompanyLogoPreviewParameterProvider :
    PreviewParameterProvider<CardCompanyUiModel.SelectCardCompany> {
    override val values: Sequence<CardCompanyUiModel.SelectCardCompany>
        get() = sequenceOf(
            CardCompany.KAKAO.toUiModel(),
            CardCompany.BC.toUiModel(),
            CardCompany.KB.toUiModel(),
            CardCompany.HANA.toUiModel(),
            CardCompany.HYUNDAE.toUiModel(),
            CardCompany.SHINHAN.toUiModel(),
            CardCompany.LOTTE.toUiModel(),
            CardCompany.WOORI.toUiModel()
        )

}