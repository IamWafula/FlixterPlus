package com.example.flixterplus

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class DetailMovie : AppCompatActivity() {
    private lateinit var movieImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView
    private lateinit var relTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieImageView = findViewById(R.id.movieImg)
        titleTextView = findViewById(R.id.movieTitle)
        overviewTextView = findViewById(R.id.imageOverview)
        relTextView = findViewById(R.id.movieRelease)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        titleTextView.text = movie.name.ifEmpty { movie.original_title }
        overviewTextView.text = movie.overview
        relTextView.text = movie.release_date.ifEmpty { movie.first_air_date }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
            .override(800,1200)
            .centerCrop()
            .into(movieImageView)
    }

}
