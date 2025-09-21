package woowacourse.payments.ui.newcard.uiModel

sealed interface NewCardMode {
    data object CreateMode : NewCardMode
    data object EditMode : NewCardMode
}