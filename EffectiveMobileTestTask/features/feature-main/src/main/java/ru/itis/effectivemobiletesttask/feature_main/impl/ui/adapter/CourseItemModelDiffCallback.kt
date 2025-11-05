package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class CourseItemModelDiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is CourseItemModel && newItem is CourseItemModel -> oldItem.course.id == newItem.course.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is CourseItemModel && newItem is CourseItemModel -> {
                oldItem.course.id == newItem.course.id &&
                        oldItem.course.hasLike == newItem.course.hasLike &&
                        oldItem.course.title == newItem.course.title &&
                        oldItem.course.text == newItem.course.text &&
                        oldItem.course.price == newItem.course.price &&
                        oldItem.course.rate == newItem.course.rate &&
                        oldItem.course.publishDate == newItem.course.publishDate
            }
            else -> false
        }
    }
}