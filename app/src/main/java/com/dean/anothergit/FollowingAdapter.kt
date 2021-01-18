package com.dean.anothergit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user.view.*

class FollowingAdapter(private val listDataFollowing: ArrayList<DataUsers>) :
    RecyclerView.Adapter<FollowingAdapter.ListDataHolder>() {

    fun setData(item: ArrayList<DataUsers>) {
        listDataFollowing.clear()
        listDataFollowing.addAll(item)
        notifyDataSetChanged()
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowing: DataUsers) {
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDataFollowing.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataFollowing[position])
    }
}