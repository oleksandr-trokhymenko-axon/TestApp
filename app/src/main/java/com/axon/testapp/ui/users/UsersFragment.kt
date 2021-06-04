package com.axon.testapp.ui.users

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.axon.testapp.R
import com.axon.testapp.data.entities.User
import com.axon.testapp.databinding.UsersFragmentBinding
import com.axon.testapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<UsersFragmentBinding>(), UsersAdapter.UserItemListener {
    override fun creatingBinding(parent: ViewGroup?): UsersFragmentBinding = inflate()
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupRecyclerView(users: List<User>) {
        adapter = UsersAdapter(this, users)
        binding.usersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRv.adapter = adapter
    }

    private fun setupList() {
        lifecycleScope.launch {
            viewModel.users.collect { users ->
                Log.d("qwerty", "Hello world, its coroutine is working! :)")
                Log.d("qwerty", users.toString())
//                setupRecyclerView(users)
            }
        }
    }

    override fun onClickedUser(userUuid: String) {
        findNavController().navigate(
            R.id.action_usersFragment_to_userDetailFragment,
            bundleOf("uuid" to userUuid)
        )
    }
}