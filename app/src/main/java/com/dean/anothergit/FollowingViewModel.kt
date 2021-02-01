package com.dean.anothergit


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FollowingViewModel : ViewModel() {
    val listFollowings = MutableLiveData<ArrayList<DataFollowing>>()

    fun setFollowing(username: String) {
        val listItems = ArrayList<DataFollowing>()

        val url = "https://api.github.com/users/$username/following"

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