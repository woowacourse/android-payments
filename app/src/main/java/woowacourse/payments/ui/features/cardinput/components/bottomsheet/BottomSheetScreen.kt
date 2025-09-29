package woowacourse.payments.ui.features.cardinput.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onItemClick: (CardCompanyUiModel) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        FlowRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, start = 30.dp, end = 30.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = COLUMN_COUNT,
        ) {
            CardCompanyUiModel.entries.filter { it != CardCompanyUiModel.UNKNOWN }.forEach {
                BottomSheetCardCompanySelectableItem(
                    modifier = Modifier,
                    onClick = { onItemClick(it) },
                    value = it,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    device = "spec:width=750px,height=1334px,dpi=326",
    name = "Tiny Phone",
)
@Composable
fun BottomSheetScreenPreview() {
    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
        )

    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {},
            onItemClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    device = "spec:width=1080px,height=2220px,dpi=440",
    name = "Small Phone",
)
@Composable
fun BottomSheetScreenSmallPhonePreview() {
    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
        )

    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {},
            onItemClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    device = "spec:width=1440px,height=3120px,dpi=560",
    name = "Large Phone",
)
@Composable
fun BottomSheetScreenLargePhonePreview() {
    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
        )

    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {},
            onItemClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    device = "spec:width=2208px,height=1840px,dpi=420",
    name = "Foldable Unfolded",
)
@Composable
fun BottomSheetScreenFoldablePreview() {
    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
        )

    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {},
            onItemClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "spec:width=2560px,height=1800px,dpi=320", name = "Tablet")
@Composable
fun BottomSheetScreenTabletPreview() {
    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
        )

    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {},
            onItemClick = {},
        )
    }
}
