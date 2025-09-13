package woowacourse.payments.ui.extension

import androidx.lifecycle.MutableLiveData

fun <T : Any> MutableLiveData<T>.update(transform: T.() -> T) {
    value?.let { safeValue -> value = safeValue.transform() }
}
