package woowacourse.payments.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class CardCompaniesUiModel(
    val items: List<CardCompanyUiModel>,
)
