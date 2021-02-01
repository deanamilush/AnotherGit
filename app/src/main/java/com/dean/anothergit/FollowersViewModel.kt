package com.dean.anothergit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<DataFollowers>>()

    fun setFollowers(username: String) {
    val listItems = ArrayList<DataFollowers>()

    val url = "https://api.github.com/users/$username/followers"

    val asyncClient = AsyncHttpClient()
    asyncClient.addHeader("Authorization", "token 3af2debd7f817177d112ffed4c98f1a1aea8ed57")
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
                    val follower = responseObject.getJSONObject(i)
                    val followerItems = DataFollowers()
                    followerItems.username = follower.getString("login")
                    followerItems.avatar = follower.getString("avatar_url")
                    listItems.add(followerItems)
                    Log.d("FOLLOWER", url)
                }
                listFollowers.postValue(listItems)
            } catch (e: Exception) {
                Log.d("Follower Exception", e.message.toString())
            }
        }

        override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
        ) {
            Log.d("Follower onFailure", error?.message.toString())
        }
    })
}

    fun getFollowers(): LiveData<ArrayList<DataFollowers>> {
    return listFollowers
    }
}