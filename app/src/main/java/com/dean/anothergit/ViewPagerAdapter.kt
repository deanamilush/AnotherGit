package com.dean.anothergit

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var username = "test"

    companion object {
        const val USERNAME = "username"
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.following, R.string.followers)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FragmentFollowing()
                val mBundle = Bundle()
                mBundle.putString(USERNAME, getData())
                fragment.arguments = mBundle
            }
            1 -> fragment = FragmentFollowers()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }

    fun setData(user: String) {
        username = user
    }

    private fun getData(): String {
        return username
    }
}