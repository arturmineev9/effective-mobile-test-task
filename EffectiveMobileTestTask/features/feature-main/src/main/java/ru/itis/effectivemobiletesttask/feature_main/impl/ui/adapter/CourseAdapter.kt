package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.itis.effectivemobiletesttask.core_common.model.Course

class CourseAdapter(
    onFavoriteClick: (Course) -> Unit,
    onDetailsClick: (Course) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(
    CourseItemModelDiffCallback()
) {
    init {
        delegatesManager.addDelegate(courseItemDelegate)
    }
}
