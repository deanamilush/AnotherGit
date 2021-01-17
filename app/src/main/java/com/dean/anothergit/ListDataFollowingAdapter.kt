package com.dean.anothergit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.anothergit.databinding.ItemUserBinding
import kotlinx.android.synthetic.main.item_user.view.*

class ListDataFollowingAdapter(private val listDataFollowing: ArrayList<DataUsers>) :
    RecyclerView.Adapter<ListDataFollowingAdapter.ListDataHolder>() {

    fun setData(item: ArrayList<DataUsers>) {
        listDataFollowing.clear()
        listDataFollowing.addAll(item)
        notifyDataSetChanged()
    }

    inner class ListDataHolder (private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (user: DataUsers){
            with(binding){
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(55,55))
                    .into(avatar)

                fullName.text = user.name
                username.text = user.username

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListDataHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDataFollowing.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataFollowing[position])
    }
}