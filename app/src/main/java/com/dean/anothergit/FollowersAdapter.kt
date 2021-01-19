package com.dean.anothergit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_followers.view.*

class FollowersAdapter
    : RecyclerView.Adapter<FollowersAdapter.ListDataHolder>() {
    private val mData = ArrayList<DataFollowers>()

    fun setData(items: ArrayList<DataFollowers>){
        mData.clear()
        mData.addAll(items)
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
        return mData.size
    }
    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(mData[position])
    }
    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowers: DataFollowers) {
            with(itemView) {
                Glide.with(itemView.context)
                        .load(dataFollowers.avatar)
                        .apply(RequestOptions().override(50, 50))
                        .into(avatar_followers)

                username_followers.text = dataFollowers.username
            }
        }
    }
}