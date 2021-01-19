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
import kotlinx.android.synthetic.main.fragment_followers.*

class FragmentFollowers : Fragment() {

    private lateinit var adapter: FollowersAdapter
    private lateinit var followerViewModel: FollowersViewModel
    companion object {

        const val ARG_USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showRecyclerView()
        rvFollowers.setHasFixedSize(true)
        loadingIndicator(true)

        Log.d("BundleFragment1", "$savedInstanceState, $arguments")
        if (arguments != null) {
            val username = arguments?.getString(ARG_USERNAME)
            Log.d("BundleFragment1", "$arguments")
            followerViewModel.setFollowers(username.toString())
        }
    }

    private fun showRecyclerView() {
        adapter = FollowersAdapter()
        adapter.notifyDataSetChanged()

        rvFollowers.layoutManager = LinearLayoutManager(context)
        rvFollowers.adapter = adapter

        followerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(FollowersViewModel::class.java)

        followerViewModel.getFollowers().observe(viewLifecycleOwner, Observer { followerItems ->
            if (followerItems != null) {
                adapter.setData(followerItems)
                loadingIndicator(false)
            }
        })
    }

    private fun loadingIndicator(state: Boolean) {
        if (state) {
            progressbarFollowers.visibility = View.VISIBLE
        } else {
            progressbarFollowers.visibility = View.GONE
        }
    }
}