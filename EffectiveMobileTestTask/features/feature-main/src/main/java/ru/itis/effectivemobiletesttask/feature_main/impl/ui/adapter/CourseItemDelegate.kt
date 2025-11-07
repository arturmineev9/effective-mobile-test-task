package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.itis.effectivemobiletesttask.core_utils.DateFormatter
import ru.itis.effectivemobiletesttask.feature_main.databinding.ItemCourseBinding
import ru.itis.effectivemobiletesttask.feature_main.R

fun courseItemDelegate() =
    adapterDelegateViewBinding<CourseItemModel, CourseItemModel, ItemCourseBinding>(
        { layoutInflater, parent -> ItemCourseBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind { payloads ->
            val model = item
            with(binding) {
                if (payloads.contains("likeChanged")) {
                    val iconRes = if (model.course.hasLike) {
                        R.drawable.ic_bookmark_filled
                    } else {
                        R.drawable.ic_bookmark
                    }
                    favoriteButton.setImageResource(iconRes)
                    return@bind
                }

                titleText.text = model.course.title
                descriptionText.text = model.course.text
                priceText.text = context.getString(R.string.price_text, model.course.price)
                ratingText.text = model.course.rate.toString()
                dateText.text = DateFormatter.formatPublishDate(model.course.publishDate)

                val iconRes = if (model.course.hasLike) {
                    R.drawable.ic_bookmark_filled
                } else {
                    R.drawable.ic_bookmark
                }
                favoriteButton.setImageResource(iconRes)

                val imageIndex = (model.course.id % 3).toInt()
                val imageRes = when (imageIndex) {
                    0 -> R.drawable.course_image_1
                    1 -> R.drawable.course_image_2
                    else -> R.drawable.course_image_3
                }
                binding.courseImage.setImageResource(imageRes)

                favoriteButton.setOnClickListener {
                    model.onFavoriteClick(model.course)
                }

                detailText.setOnClickListener {
                    model.onDetailsClick(model.course)
                }
            }
        }
    }
