package com.dean.anothergit.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dean.anothergit.R
import com.dean.anothergit.followers.FragmentFollowers
import com.dean.anothergit.following.FragmentFollowing

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.following, R.string.followers)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FragmentFollowing()
            }
            1 -> {
                fragment = FragmentFollowers()
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
}