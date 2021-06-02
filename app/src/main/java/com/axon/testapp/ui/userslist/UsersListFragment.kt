package com.axon.testapp.ui.userslist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.axon.testapp.data.User
import com.axon.testapp.databinding.FragmentUsersListBinding
import com.axon.testapp.ui.BaseFragment
import com.axon.testapp.ui.adapters.UsersListAdapter
import com.axon.testapp.ui.adapters.UsersListLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : BaseFragment<FragmentUsersListBinding>(),
    UsersListAdapter.OnItemClickListener {
    private val viewModel by viewModels<UsersListViewModel>()

    override fun creatingBinding(parent: ViewGroup?): FragmentUsersListBinding = inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UsersListAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UsersListLoadStateAdapter { adapter.retry() },
                footer = UsersListLoadStateAdapter { adapter.retry() }
            )

            retryButton.setOnClickListener {
                adapter.retry()
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.Loading
                retryButton.isVisible = loadState.source.refresh is LoadState.Loading
                textViewError.isVisible = loadState.source.refresh is LoadState.Loading

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewError.isVisible = true
                } else {
                    textViewError.isVisible = false
                }
            }
        }

        viewModel.usersListLiveData.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onItemClick(user: User) {
        val action = UsersListFragmentDirections.actionUsersListFragmentToDetailsFragment()
        findNavController().navigate(action)
    }
}