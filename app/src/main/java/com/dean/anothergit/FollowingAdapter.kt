package com.dean.anothergit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user.view.*

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.ListDataHolder>() {

    private val mData = ArrayList<DataFollowing>()

    fun setData(item: ArrayList<DataFollowing>) {
        mData.clear()
        mData.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
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
                        .apply(RequestOptions().override(100, 100))
                        .into(avatar)

                fullName.text = dataFollowing.name
                username.text = dataFollowing.username
            }
        }
    }
}