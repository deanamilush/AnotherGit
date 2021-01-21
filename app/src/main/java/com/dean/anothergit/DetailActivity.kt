package com.dean.anothergit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.user_name
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.item_user.view.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setData()


        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f

    }


    private fun setData() {
        val dataUser = intent.getParcelableExtra<DataUsers>(EXTRA_DETAIL) as DataUsers
        Glide.with(this)
            .load(dataUser.avatar)
            .apply(RequestOptions().override(130, 110))
            .into(avatars)
        full_names.text = dataUser.name
        user_name.text = dataUser.username
        user_company.text = dataUser.company
        user_location.text = dataUser.location
        user_repository.text = dataUser.repository
        user_followers.text = dataUser.followers
        user_following.text = dataUser.following
    }
}