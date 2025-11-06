package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.itis.effectivemobiletesttask.feature_main.databinding.ItemCourseBinding
import ru.itis.effectivemobiletesttask.feature_main.R

fun courseItemDelegate() =
    adapterDelegateViewBinding<CourseItemModel, CourseItemModel, ItemCourseBinding>(
        { layoutInflater, parent -> ItemCourseBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            val model = item
            with(binding) {
                titleText.text = model.course.title
                descriptionText.text = model.course.text
                priceText.text = context.getString(R.string.price_text, model.course.price)
                ratingText.text = model.course.rate.toString()
                dateText.text = model.course.publishDate

                val iconRes = if (model.course.hasLike) {
                    R.drawable.ic_bookmark_filled
                } else {
                    R.drawable.ic_bookmark
                }
                favoriteButton.setImageResource(iconRes)

                favoriteButton.setOnClickListener {
                    model.onFavoriteClick(model.course)
                }

                detailText.setOnClickListener {
                    model.onDetailsClick(model.course)
                }
            }
        }
    }
