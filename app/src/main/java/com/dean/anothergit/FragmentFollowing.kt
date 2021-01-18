package com.dean.anothergit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_following.*

class FragmentFollowing : Fragment() {

    private lateinit var adapter: FollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel
    companion object {

        const val ARG_USERNAME = "username"
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRecyclerView()
        rvFollowing.setHasFixedSize(true)
        showLoading(true)

        if (arguments != null) {
            val username = arguments?.getString(ARG_USERNAME)
            followingViewModel.setFollowing(username.toString())
            Log.d("BundleFragment1", "$arguments")
        }
    }

    private fun showRecyclerView() {
        adapter = FollowingAdapter()
        adapter.notifyDataSetChanged()

        rvFollowing.layoutManager = LinearLayoutManager(context)
        rvFollowing.adapter = adapter

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(FollowingViewModel::class.java)

        followingViewModel.getFollowing().observe(viewLifecycleOwner, Observer{ followingItems ->
            if (followingItems != null) {
                adapter.setData(followingItems)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressbarFollowing.visibility = View.VISIBLE
        } else {
            progressbarFollowing.visibility = View.INVISIBLE
        }
    }
}