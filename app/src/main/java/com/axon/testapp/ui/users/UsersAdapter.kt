package com.axon.testapp.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axon.testapp.data.entities.User
import com.axon.testapp.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class UsersAdapter(private val listener: UserItemListener, private var listOfUsers: List<User>) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listOfUsers[position])
    }

    override fun getItemCount(): Int = listOfUsers.size

    interface UserItemListener {
        fun onClickedUser(userUuid: String)
    }
}

class UserViewHolder(
    private val itemUserBinding: ItemUserBinding,
    private val listener: UsersAdapter.UserItemListener
) : RecyclerView.ViewHolder(itemUserBinding.root), View.OnClickListener {

    private lateinit var user: User

    init {
        itemUserBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: User) {
        this.user = item
        itemUserBinding.apply {
            nameTv.text = item.name.first + " " + item.name.last
            ageTv.text = item.dob.age.toString() + " y.o."
            countryTv.text = item.location.country

            Glide.with(itemUserBinding.root)
                .load(item.picture.large)
                .transform(CircleCrop())
                .into(itemUserBinding.imageView)
        }
    }

    override fun onClick(v: View?) {
        listener.onClickedUser(user.login.uuid)
    }

}