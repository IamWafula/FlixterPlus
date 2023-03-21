package com.example.flixterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject


private const val MAIN_KEY = BuildConfig.API_KEY
private const val URL = "https://api.themoviedb.org/3/trending/movie/week?api_key=${MAIN_KEY}"
private const val SHOW_URL = "https://api.themoviedb.org/3/trending/tv/week?api_key=${MAIN_KEY}"

class MainActivity : AppCompatActivity() {

    private var movies = mutableListOf<Movie>()
    private lateinit var moviesRv : RecyclerView
    private var shows = mutableListOf<Movie>()
    private lateinit var showsRv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        moviesRv = findViewById(R.id.moviesRv)
        val mAdapter = moviesAdapter(this, movies)
        moviesRv.adapter = mAdapter

        moviesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val client = AsyncHttpClient()

        client.get(URL, object:JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("FAILURE", response.toString())
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                var gson = Gson()
                var moviesJSON : JSONObject = json?.jsonObject as JSONObject
                var moviesRawJSON : String = moviesJSON.get("results").toString()
                var arrayMovies = object : TypeToken<List<Movie>> () {}.type

                var newMovies : List<Movie> = gson.fromJson(moviesRawJSON, arrayMovies)
                movies.addAll(newMovies)
                Log.d("SUCCESS", movies[0].overview)
                mAdapter.notifyDataSetChanged()

            }

        })


        showsRv = findViewById(R.id.showRv)
        val sAdapter = moviesAdapter(this, shows)
        showsRv.adapter = sAdapter

        showsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val client2 = AsyncHttpClient()

        client2.get(SHOW_URL, object:JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("FAILURE", response.toString())
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                var gson = Gson()
                var showsJSON : JSONObject = json?.jsonObject as JSONObject
                var showsRawJSON : String = showsJSON.get("results").toString()
                Log.d("SHOW SUCCESS", showsRawJSON)
                var arrayShows = object : TypeToken<List<Movie>> () {}.type

                var newShows : List<Movie> = gson.fromJson(showsRawJSON, arrayShows)
                shows.addAll(newShows)

                sAdapter.notifyDataSetChanged()

            }

        })


    }

}