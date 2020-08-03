package com.sweatworks.homework.common.utils

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sweatworks.domain.model.User
import com.sweatworks.homework.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun ImageView.loadImageUrl(url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("bindUserName")
    fun TextView.bindUserName(user: User) {
        text = String.format(
            context.getString(R.string.user_name),
            user.firstName,
            user.lastName,
            user.country
        )
    }

    @JvmStatic
    @BindingAdapter("bindUserAge")
    fun TextView.bindUserAge(user: User) {
        text = String.format(
            context.getString(R.string.user_age),
            user.age,
            user.city
        )
    }

    @JvmStatic
    @BindingAdapter("bindContactInfo")
    fun TextView.bindContactInfo(user: User) {
        text = String.format(
            context.getString(R.string.user_contact),
            user.email,
            user.phoneNumber,
            user.cellPhoneNumber
        )
    }

    @JvmStatic
    @BindingAdapter("itemOrientation")
    fun CardView.itemOrientation(orientation: Int) {
        val params: ViewGroup.LayoutParams = layoutParams
        params.width =
            if (orientation == LinearLayout.HORIZONTAL) {
                resources.getDimensionPixelSize(
                    R.dimen.horizontal_item_width
                )
            } else {
                MATCH_PARENT
            }
        layoutParams = params
    }

    @JvmStatic
    @BindingAdapter("toggleFavorite")
    fun ImageView.toggleFavorite(isFavorite: Boolean) {
        val resource = if (isFavorite) {
            R.drawable.ic_full_star
        } else {
            R.drawable.ic_empty_star
        }
        setImageDrawable(
            resources.getDrawable(resource, context.theme)
        )
    }
}