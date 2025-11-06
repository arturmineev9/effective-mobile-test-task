package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class CourseDiffCallback : DiffUtil.ItemCallback<CourseItemModel>() {
    override fun areItemsTheSame(oldItem: CourseItemModel, newItem: CourseItemModel): Boolean =
        oldItem.course.id == newItem.course.id

    override fun areContentsTheSame(oldItem: CourseItemModel, newItem: CourseItemModel): Boolean =
        oldItem.course == newItem.course

    override fun getChangePayload(oldItem: CourseItemModel, newItem: CourseItemModel): Any? {
        return if (oldItem.course.hasLike != newItem.course.hasLike) {
            "likeChanged"
        } else {
            null
        }
    }
}
