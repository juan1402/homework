package com.sweatworks.homework.ui.detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweatworks.homework.BR
import com.sweatworks.homework.R
import com.sweatworks.homework.common.extensions.addToContacts
import com.sweatworks.homework.common.extensions.openGoogleMaps
import com.sweatworks.homework.common.extensions.paginate
import com.sweatworks.homework.common.extensions.subscribe
import com.sweatworks.homework.databinding.FragmentDetailBinding
import com.sweatworks.homework.ui.base.BaseActivity.Companion.BASE_ARGUMENTS
import com.sweatworks.homework.ui.base.BaseFragment
import com.sweatworks.homework.ui.home.adapter.FavoriteClick
import com.sweatworks.homework.ui.home.adapter.ItemClick
import com.sweatworks.homework.ui.home.adapter.ItemDecorator
import com.sweatworks.homework.ui.home.adapter.UserAdapter
import com.sweatworks.homework.ui.home.adapter.UserItemActions
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(BR.viewModel, R.layout.fragment_detail) {

    override val viewModel: DetailViewModel by viewModel()

    private val contentAdapter = UserAdapter(LinearLayoutManager.VERTICAL)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val space = resources
            .getDimensionPixelSize(R.dimen.common_divider_space)
        val decorator = ItemDecorator(space)
        setUpViewModel()
        setUpUserGridList(decorator)
        subscribeObservers()
    }

    private fun setUpViewModel() {
        viewModel.currentUser.set(
            arguments?.getBundle(BASE_ARGUMENTS)
                ?.getParcelable(BASE_ARGUMENTS)
        )
    }

    private fun setUpUserGridList(decorator: ItemDecorator) =
        binding.detailGrid.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(decorator)
            adapter = contentAdapter
            paginate { viewModel.loadMoreUsers() }
        }

    private fun subscribeObservers() = with(viewModel) {
        contentAdapter.userItemActions.subscribe(viewLifecycleOwner, ::handleAdapterAction)
        detailActions.subscribe(viewLifecycleOwner, ::handleDetailAction)
        userData.subscribe(viewLifecycleOwner) { contentAdapter.update(it) }
    }

    private fun handleDetailAction(action: DetailActions) {
        when (action) {
            is OpenGoogleMap -> requireContext()
                .openGoogleMaps(action.user.latitude, action.user.longitude)
            is AddToContacts -> requireContext().addToContacts(action.user)
        }
    }

    private fun handleAdapterAction(action: UserItemActions) {
        when (action) {
            is ItemClick -> viewModel.fireDetailEvent(action.user)
            is FavoriteClick -> {
                viewModel.addToFavorites(action.user)
                contentAdapter.replaceItem(action.user)
            }
        }
    }
}