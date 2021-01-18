package com.dean.anothergit


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class FollowingViewModel : ViewModel() {
    val listFollowings = MutableLiveData<ArrayList<DataFollowing>>()

    fun setFollowing(username: String) {
        val listItems = ArrayList<DataFollowing>()

        //val apiKey = BuildConfig.API_KEY
        val url = "https://api.github.com/users/$username/following"
        val asyncClient = AsyncHttpClient()
        asyncClient.addHeader("Authorization", "token 4526e8b4e8787d4fec8cb665ae4d86ba07c9f530")
        asyncClient.addHeader("User-Agent", "request")
        asyncClient.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONArray(result)

                    for (i in 0 until responseObject.length()) {
                        val following = responseObject.getJSONObject(i)
                        val followingItems = DataFollowing()
                        followingItems.username = following.getString("login")
                        followingItems.avatar = following.getString("avatar_url")
                        listItems.add(followingItems)
                        Log.d("FOLLOWING", url)
                    }
                    listFollowings.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Following Exception", e.message.toString())
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                Log.d("Following onFailure", error?.message.toString())
            }
        })
    }

    fun getFollowing(): LiveData<ArrayList<DataFollowing>> {
        return listFollowings
    }
}