package woowacourse.payments.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopAppBar(
    onRegistrationClick: () -> Unit,
    isVisibleRegistrationButton: Boolean,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.W400,
                color = Color.Black,
                lineHeight = 28.sp,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        },
        actions = {
            if (isVisibleRegistrationButton) {
                TextButton(
                    onClick = onRegistrationClick,
                    modifier = Modifier.semantics { contentDescription = "카드 목록 앱 바 추가 버튼" },
                ) {
                    Text(
                        text = stringResource(R.string.cards_top_app_bar_add),
                        color = Color.Black,
                        lineHeight = 28.sp,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true, name = "메인 화면 앱 바 추가 버튼 비활성화")
@Composable
private fun InvisibleRegistrationButtonPreview() {
    CardsTopAppBar(
        onRegistrationClick = { },
        isVisibleRegistrationButton = false,
    )
}

@Preview(showBackground = true, name = "메인 화면 앱 바 추가 버튼 활성화")
@Composable
private fun VisibleRegistrationButtonPreview() {
    CardsTopAppBar(
        onRegistrationClick = { },
        isVisibleRegistrationButton = true,
    )
}
