package com.dean.anothergit.followers

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
import kotlinx.android.synthetic.main.fragment_followers.*

class FragmentFollowers : Fragment() {

    companion object {
        val TAG = FragmentFollowers::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private val listData: ArrayList<DataFollowers> = ArrayList()
    private lateinit var adapter: FollowersAdapter
    private lateinit var followerViewModel: FollowersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowersAdapter(listData)
        followerViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        val dataUser = activity!!.intent.getParcelableExtra<DataUsers>(EXTRA_DETAIL) as DataUsers
        config()

        followerViewModel.getDataGit(activity!!.applicationContext, dataUser.username.toString())
        showLoading(true)

        followerViewModel.getListFollower().observe(activity!!, Observer { listFollower ->
            if (listFollower != null) {
                adapter.setData(listFollower)
                showLoading(false)
            }
        })
    }

    private fun config() {
        rvFollowers.layoutManager = LinearLayoutManager(activity)
        rvFollowers.setHasFixedSize(true)
        rvFollowers.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressbarFollowers.visibility = View.VISIBLE
        } else {
            progressbarFollowers.visibility = View.INVISIBLE
        }
    }

}