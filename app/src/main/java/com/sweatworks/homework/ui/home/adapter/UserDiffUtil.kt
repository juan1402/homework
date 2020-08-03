package com.sweatworks.homework.ui.home.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.sweatworks.domain.model.User

class UserDiffUtil(private val oldList: List<User>, private val newList: List<User>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].firstName === newList[newItemPosition].firstName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].age == newList[newItemPosition].age
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}