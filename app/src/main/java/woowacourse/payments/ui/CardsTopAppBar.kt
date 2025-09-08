package woowacourse.payments.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopAppBar(
    isAddActionVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Payments",
                fontSize = 22.sp,
            )
        },
        modifier = modifier,
        actions = {
            if (isAddActionVisible) {
                Text(
                    text = "추가",
                    modifier =
                        Modifier
                            .padding(
                                horizontal = 20.dp,
                                vertical = 9.dp,
                            ).semantics {
                                contentDescription = "새 카드 등록 버튼"
                            },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                )
            }
        },
    )
}

@Preview
@Composable
private fun CardsTopAppBarPreview(
    @PreviewParameter(CardsTopAppBarPreviewParameterProvider::class) isAddActionVisible: Boolean,
) {
    CardsTopAppBar(
        isAddActionVisible = isAddActionVisible,
    )
}

private class CardsTopAppBarPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}
