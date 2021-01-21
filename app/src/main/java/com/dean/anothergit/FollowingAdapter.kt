package com.dean.anothergit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_following.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class FollowingAdapter(private val listFollowing: ArrayList<DataFollowing>)
    : RecyclerView.Adapter<FollowingAdapter.ListDataHolder>() {


    fun setData(items: ArrayList<DataFollowing>){
        listFollowing.clear()
        listFollowing.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_following, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listFollowing.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listFollowing[position])
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowing: DataFollowing) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(dataFollowing.avatar)
                        .apply(RequestOptions().override(80, 80))
                        .into(avatar_following)

                username_following.text = dataFollowing.username
            }
        }
    }
}