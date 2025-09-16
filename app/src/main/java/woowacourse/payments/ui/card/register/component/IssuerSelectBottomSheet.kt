//package woowacourse.payments.ui.card.register.component
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.ExperimentalLayoutApi
//import androidx.compose.foundation.layout.FlowRow
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//
//
//private const val COLUMN_COUNT = 4
//private const val ROW_COUNT = 2
//
//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun BankSelectRow() {
//    FlowRow(
//        modifier = Modifier.padding(4.dp),
//        horizontalArrangement = Arrangement.spacedBy(4.dp),
//        maxItemsInEachRow = COLUMN_COUNT,
//    ) {
//        repeat(ROW_COUNT * COLUMN_COUNT) {
//        }
//    }
//}
//
//@Preview
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun IssuerSelectBottomSheet() {
//    val modalBottomSheetState =
//        rememberModalBottomSheetState(
//            confirmValueChange = { false },
//        )
//    var selectedBank by remember {
//        mutableStateOf(BankType.NOT_SELECTED)
//    }
//    LaunchedEffect(key1 = selectedBank) {
//        if (selectedBank != BankType.NOT_SELECTED) {
//            modalBottomSheetState.hide()
//        }
//    }
//
//    ModalBottomSheet(
//        sheetState = modalBottomSheetState,
//        onDismissRequest = { },
//    ) {
//        BankSelectRow()
//    }
//}
