package com.dean.anothergit.followers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.anothergit.R
import kotlinx.android.synthetic.main.item_followers.view.*

class FollowersAdapter(private val listFollowers: ArrayList<DataFollowers>)
    : RecyclerView.Adapter<FollowersAdapter.ListDataHolder>() {

    fun setData(items: ArrayList<DataFollowers>){
        listFollowers.clear()
        listFollowers.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ListDataHolder {
        return ListDataHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_followers, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listFollowers.size
    }
    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listFollowers[position])
    }
    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowers: DataFollowers) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(dataFollowers.avatar)
                        .apply(RequestOptions().override(80, 80))
                        .into(avatar_followers)

                username_followers.text = dataFollowers.username
            }
        }
    }
}