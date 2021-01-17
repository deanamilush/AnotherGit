package com.dean.anothergit

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.anothergit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private var listDataUser: ArrayList<DataUsers> = ArrayList()
    private lateinit var listAdapter: DataUsersAdapter
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)

        listAdapter = DataUsersAdapter(listDataUser)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        getConfig()
        runGetDataGit()
        configMainViewModel(listAdapter)
    }

    private fun getConfig() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        listAdapter.notifyDataSetChanged()
        binding.rvUser.adapter = listAdapter
        val listData = DataUsersAdapter(listDataUser)
        listData.setOnItemClickCallback(object : DataUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(dataUsers: DataUsers) {
                showSelectedUser(dataUsers)
            }
        })
    }
    private fun showSelectedUser(dataUser: DataUsers) {
        DataUsers(
            dataUser.username,
            dataUser.name,
            dataUser.avatar,
            /*dataUser.company,
            dataUser.location,
            dataUser.repository,
            dataUser.followers,
            dataUser.following*/
        )
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DETAIL, dataUser)

        this@MainActivity.startActivity(intent)
        Toast.makeText(
            this,
            "${dataUser.name}",
            Toast.LENGTH_SHORT
        ).show()

    }


    private fun runGetDataGit() {
        mainViewModel.getDataGit(applicationContext)
        showLoading(true)
    }


    private fun configMainViewModel(adapter: DataUsersAdapter) {
        mainViewModel.getListUsers().observe(this, Observer { listUsers ->
            if (listUsers != null) {
                adapter.setData(listUsers)
                showLoading(false)
            }
        })
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.loadingProgress.visibility = View.VISIBLE
        } else {
            binding.loadingProgress.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listDataUser.clear()
                    getConfig()
                    mainViewModel.getDataSearch(query, applicationContext)
                    showLoading(true)
                    configMainViewModel(listAdapter)
                } else {
                    return true
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}