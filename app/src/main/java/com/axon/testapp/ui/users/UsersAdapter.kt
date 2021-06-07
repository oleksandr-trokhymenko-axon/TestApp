package com.axon.testapp.ui.users

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.axon.testapp.R
import com.axon.testapp.data.entities.User
import com.axon.testapp.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class UsersAdapter(
    private val listener: UserItemListener
) :
    PagingDataAdapter<User, UsersAdapter.UserViewHolder>(USERS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    interface UserItemListener {
        fun onClickedUser(userUuid: String)
    }

    inner class UserViewHolder(
        private val itemUserBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(itemUserBinding.root), View.OnClickListener {

        private lateinit var user: User

        init {
            itemUserBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: User) {
            this.user = item
            itemUserBinding.apply {
                tvName.text = item.name.first + " " + item.name.last
                tvGender.text = "Gender: " + item.gender
                tvAge.text = "Age: " + item.dob.age.toString() + " y.o."
                tvPlace.text = "Place: " + item.location.country + ", " + item.location.city
                btnShowMore.setOnCheckedChangeListener { _, isChecked ->
                    subItem.isVisible = isChecked
                }

                Glide.with(itemUserBinding.root)
                    .load(item.picture.large)
                    .transform(CircleCrop())
                    .into(itemUserBinding.ivChooseUser)
            }
        }

        override fun onClick(v: View?) {
            listener.onClickedUser(user.login.uuid)
        }
    }

    companion object {
        private val USERS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.login.uuid == newItem.login.uuid

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}
