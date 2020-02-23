package com.arttttt.swapisamplemvi.ui.base.recyclerview

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arttttt.swapisamplemvi.utils.extensions.onNext
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ListDifferAdapter @Inject constructor(
    diffCallback: DiffUtil.ItemCallback<IListItem>,
    delegates: Set<AdapterDelegate<List<IListItem>>>
) : AsyncListDifferDelegationAdapter<IListItem>(diffCallback, *delegates.toTypedArray()) {

    private val loadMorePublisher = PublishSubject.create<Unit>()
    val loadMoreObservable: Observable<Unit> = loadMorePublisher.share()
    var remainingItemsThreshold = 3

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any?>) {
        if (position >= items.size - remainingItemsThreshold) {
            loadMorePublisher.onNext()
        }
        super.onBindViewHolder(holder, position, payloads)
    }
}
