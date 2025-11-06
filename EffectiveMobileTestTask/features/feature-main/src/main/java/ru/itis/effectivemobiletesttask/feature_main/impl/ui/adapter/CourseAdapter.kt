package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.itis.effectivemobiletesttask.core_common.model.Course

class CoursesAdapter :
    AsyncListDifferDelegationAdapter<CourseItemModel>(CourseDiffCallback()) {

    init {
        delegatesManager.addDelegate(courseItemDelegate())
    }

    class CourseDiffCallback : DiffUtil.ItemCallback<CourseItemModel>() {
        override fun areItemsTheSame(oldItem: CourseItemModel, newItem: CourseItemModel): Boolean =
            oldItem.course.id == newItem.course.id

        override fun areContentsTheSame(oldItem: CourseItemModel, newItem: CourseItemModel): Boolean =
            oldItem == newItem
    }
}

