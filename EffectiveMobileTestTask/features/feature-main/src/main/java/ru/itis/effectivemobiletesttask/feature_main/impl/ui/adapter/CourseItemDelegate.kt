package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.itis.effectivemobiletesttask.feature_main.databinding.ItemCourseBinding
import ru.itis.effectivemobiletesttask.feature_main.R

val courseItemDelegate = adapterDelegateViewBinding<CourseItemModel, Any, ItemCourseBinding>(
    { layoutInflater, parent -> ItemCourseBinding.inflate(layoutInflater, parent, false) }
) {
    val model = item

    // Привязка данных
    with(binding) {
        titleText.text = model.course.title
        descriptionText.text = model.course.text
        priceText.text = "${model.course.price} ₽"
        ratingText.text = model.course.rate.toString()
        dateText.text = model.course.publishDate

        // Цвет иконки избранного
        val iconRes = if (model.course.hasLike) {
            R.drawable.ic_bookmark_filled
        } else {
            R.drawable.ic_bookmark
        }
        favoriteButton.setImageResource(iconRes)

        // Клик по избранному
        favoriteButton.setOnClickListener {
            model.onFavoriteClick(model.course)
        }

        // Клик по "Подробнее"
        detailText.setOnClickListener {
            model.onDetailsClick(model.course)
        }
    }
}