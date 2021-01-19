package com.dean.anothergit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_following.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class FollowingAdapter
    : RecyclerView.Adapter<FollowingAdapter.ListDataHolder>() {
    private val mData = ArrayList<DataFollowing>()

    fun setData(items: ArrayList<DataFollowing>){
        mData.clear()
        mData.addAll(items)
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
        return mData.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowing: DataFollowing) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(dataFollowing.avatar)
                        .apply(RequestOptions().override(50, 50))
                        .into(avatar_following)

                username_following.text = dataFollowing.username
            }
        }
    }
}