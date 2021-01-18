package com.dean.anothergit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentFollowers : Fragment() {

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
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }
}