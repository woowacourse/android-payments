package woowacourse.payments.ui.model

sealed interface NewCardMode {
    data object CreateMode : NewCardMode
    data object EditMode : NewCardMode
}