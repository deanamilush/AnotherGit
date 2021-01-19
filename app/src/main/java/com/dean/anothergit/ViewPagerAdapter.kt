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
        const val EXTRA_DETAIL = "extra_detail"
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.following, R.string.followers)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FragmentFollowing()
                val mBundle = Bundle()
                mBundle.putString(EXTRA_DETAIL, getData())
                fragment.arguments = mBundle
                Log.d("BundleFragmentVP", fragment.arguments.toString())
            }
            1 -> {
                fragment = FragmentFollowers()
                val mBundle = Bundle()
                mBundle.putString(EXTRA_DETAIL, getData())
                fragment.arguments = mBundle
                Log.d("BundleFragmentVP1", fragment.arguments.toString())
            }
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