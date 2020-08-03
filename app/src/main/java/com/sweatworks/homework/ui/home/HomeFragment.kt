package com.sweatworks.homework.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweatworks.homework.BR
import com.sweatworks.homework.R
import com.sweatworks.homework.common.extensions.paginate
import com.sweatworks.homework.common.extensions.subscribe
import com.sweatworks.homework.databinding.FragmentHomeBinding
import com.sweatworks.homework.ui.base.BaseFragment
import com.sweatworks.homework.ui.home.adapter.FavoriteClick
import com.sweatworks.homework.ui.home.adapter.ItemClick
import com.sweatworks.homework.ui.home.adapter.ItemDecorator
import com.sweatworks.homework.ui.home.adapter.UserAdapter
import com.sweatworks.homework.ui.home.adapter.UserItemActions
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(BR.viewModel, R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

    private val headerAdapter = UserAdapter(LinearLayoutManager.HORIZONTAL)
    private val contentAdapter = UserAdapter(LinearLayoutManager.VERTICAL)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        val space = resources
            .getDimensionPixelSize(R.dimen.common_divider_space)
        val decorator = ItemDecorator(space)
        setUpCachedHorizontalList(decorator)
        setUpUserGridList(decorator)
        subscribeObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        (menu.findItem(R.id.search).actionView as SearchView)
            .apply {
                setOnQueryTextListener(viewModel)
                setOnCloseListener(viewModel)
            }
    }

    private fun subscribeObservers() = with(viewModel) {
        val owner = viewLifecycleOwner
        contentAdapter.userItemActions.subscribe(owner) { handleAction(contentAdapter, it) }
        headerAdapter.userItemActions.subscribe(owner) { handleAction(headerAdapter, it) }
        reloadedHeaderData.subscribe(owner) { headerAdapter.replaceAll(it) }
        searchUserData.subscribe(owner) { contentAdapter.replaceAll(it) }
        headerData.subscribe(owner) { headerAdapter.loadMore(it) }
        contentData.subscribe(owner) { contentAdapter.loadMore(it) }
    }

    private fun setUpUserGridList(decorator: ItemDecorator) =
        binding.userGrid.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(decorator)
            adapter = contentAdapter
            paginate { viewModel.loadMoreUsers() }
        }

    private fun setUpCachedHorizontalList(decorator: ItemDecorator) =
        binding.toolbar.cachedAndFavoritesCarousel.apply {
            adapter = headerAdapter
            addItemDecoration(decorator)
            paginate { viewModel.loadMoreCachedUsers() }
        }

    private fun handleAction(adapter: UserAdapter, action: UserItemActions) {
        when (action) {
            is ItemClick -> viewModel.fireDetailEvent(action.user)
            is FavoriteClick -> {
                viewModel.addToFavoriteAndReload(action.user)
                adapter.replaceItem(action.user)
            }
        }
    }
}