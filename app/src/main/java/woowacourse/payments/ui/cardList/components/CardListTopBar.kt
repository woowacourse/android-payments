package woowacourse.payments.ui.cardList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.FF000000
import woowacourse.payments.ui.theme.FFFFFFFF
import woowacourse.payments.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    onRegistrationClick: () -> Unit,
    modifier: Modifier = Modifier,
    isShowRegistrationButton: Boolean = true,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = modifier,
                text = stringResource(R.string.app_name),
                textAlign = TextAlign.Center,
            )
        },
        actions =
            {
                if (isShowRegistrationButton) {
                    Text(
                        text = "추가",
                        style = Typography.displayLarge,
                        color = FF000000,
                        modifier =
                            Modifier
                                .padding(end = 20.dp)
                                .clickable { onRegistrationClick() },
                    )
                }
            },
        modifier = modifier.background(FFFFFFFF),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListTopBarPreview() {
    AndroidpaymentsTheme {
        CardListTopBar({})
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListTopBarPreview2() {
    AndroidpaymentsTheme {
        CardListTopBar(isShowRegistrationButton = false, onRegistrationClick = {})
    }
}
