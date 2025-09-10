package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
fun CardsTopAppBar(
    onRegistrationClick: () -> Unit,
    isVisibleRegistrationButton: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(64.dp),
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.W400,
            lineHeight = 28.sp,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )

        if (isVisibleRegistrationButton) ShowRegistrationButton(onRegistrationClick)
    }
}

@Composable
private fun BoxScope.ShowRegistrationButton(onRegistrationClick: () -> Unit) {
    TextButton(
        onClick = onRegistrationClick,
        modifier =
            Modifier
                .semantics { contentDescription = "카드 목록 앱 바 추가 버튼" }
                .align(Alignment.CenterEnd),
    ) {
        Text(
            text = "추가",
            lineHeight = 28.sp,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
        )
    }
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
