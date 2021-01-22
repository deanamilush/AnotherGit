package com.dean.anothergit.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.anothergit.main.DataUsers
import com.dean.anothergit.R
import kotlinx.android.synthetic.main.fragment_following.*

class FragmentFollowing : Fragment() {

    companion object {
        val TAG = FragmentFollowing::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private var bool: Boolean = false
    private var listData: ArrayList<DataFollowing> = ArrayList()
    private lateinit var adapter: FollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowingAdapter(listData)
        followingViewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)

        val dataUser = activity!!.intent.getParcelableExtra<DataUsers>(EXTRA_DETAIL) as DataUsers
        config()

        followingViewModel.getDataGit(
                activity!!.applicationContext,
                dataUser.username.toString()
        )
        showLoading(true)

        followingViewModel.getListFollowing().observe(activity!!, Observer { listFollowing ->
            if (listFollowing != null) {
                adapter.setData(listFollowing)
                showLoading(false)
            }
        })
    }

    private fun config() {
        rvFollowing.layoutManager = LinearLayoutManager(activity)
        rvFollowing.setHasFixedSize(true)
        rvFollowing.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressbarFollowing.visibility = View.VISIBLE
        } else {
            progressbarFollowing.visibility = View.INVISIBLE
        }
    }
}