package com.axon.testapp.ui.usersdetails

import android.view.ViewGroup
import com.axon.testapp.databinding.FragmentDetailsBinding
import com.axon.testapp.ui.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun creatingBinding(parent: ViewGroup?): FragmentDetailsBinding = inflate()
}