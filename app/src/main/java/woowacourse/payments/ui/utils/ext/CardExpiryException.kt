package woowacourse.payments.ui.utils.ext

import woowacourse.payments.domain.ExpiryException

fun ExpiryException.toErrorMessage(): String? =
    when (this) {
        ExpiryException.Valid -> null
        ExpiryException.InvalidFormat -> "만료일은 4자리(MMYY)여야 합니다"
        ExpiryException.InvalidMonth -> "월은 01~12여야 합니다"
        ExpiryException.InvalidYear -> "년도 형식이 올바르지 않습니다"
        ExpiryException.Expired -> "만료된 카드입니다"
    }
