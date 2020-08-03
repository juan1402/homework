package com.sweatworks.homework.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sweatworks.domain.model.User
import com.sweatworks.homework.R
import com.sweatworks.homework.databinding.UserItemBinding

class UserAdapter(private val orientation: Int) :
    RecyclerView.Adapter<UserAdapter.UserItemHolder>() {

    val userItemActions = MutableLiveData<UserItemActions>()
    private val adapterUserData = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        )

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        holder.binding?.viewModel = UserItemViewModel(
            userItemActions,
            orientation,
            adapterUserData[position]
        )
    }

    override fun getItemCount() = adapterUserData.size

    fun replaceAll(elements: List<User>) {
        val diffCallback = UserDiffUtil(adapterUserData, elements)
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        adapterUserData.clear()
        adapterUserData.addAll(elements)
        diffResult.dispatchUpdatesTo(this)
    }

    fun loadMore(elements: List<User>) {
        val size = adapterUserData.size
        adapterUserData.addAll(elements)
        notifyItemRangeInserted(size, elements.size)
    }

    fun replaceItem(user: User) {
        val index = adapterUserData.indexOf(user)
        adapterUserData[index] = user
        notifyItemChanged(index)
    }

    class UserItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<UserItemBinding>(itemView)
    }
}