package woowacourse.payments

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class SingleLiveData<T> {
    private val liveData = MutableLiveData<Event<T>>()

    protected constructor()

    protected constructor(value: T) {
        liveData.value = Event(value)
    }

    open var value: T?
        get() = liveData.value?.content
        protected set(value) {
            liveData.value = value?.let { Event(it) }
        }

    protected open fun postValue(value: T) {
        liveData.postValue(Event(value))
    }

    fun observe(
        owner: LifecycleOwner,
        onChanged: (T) -> Unit,
    ) {
        liveData.observe(owner) { it.getContentIfNotHandled()?.let(onChanged) }
    }

    fun removeObserver(observer: Observer<Event<T>>) = liveData.removeObserver(observer)

    fun observeForever(onChanged: (T) -> Unit): Observer<Event<T>> {
        val observer = Observer<Event<T>> { it.getContentIfNotHandled()?.let(onChanged) }
        liveData.observeForever(observer)
        return observer
    }
}

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class Event<out T>(
    val content: T,
) {
    var hasBeenHandled = false
        private set

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
}
