package com.dean.anothergit

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var username: String? = null

    private val tabTitles = intArrayOf(
            R.string.following,
            R.string.followers
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentFollowing()
            1 -> fragment = FragmentFollowers()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }
}