package com.example.flixterplus

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

const val MOVIE_EXTRA = "MOVIE_EXTRA"

class moviesAdapter(private val context: Context, private val movies: MutableList<Movie>): RecyclerView.Adapter<moviesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titleTextView = itemView.findViewById<TextView>(R.id.movieName)
        private val movieImage = itemView.findViewById<ImageView>(R.id.movieImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            if (movie.original_title.isNullOrBlank()) {
                titleTextView.text = movie.name
            } else {
                titleTextView.text = movie.original_title
            }

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
                .override(300,500)
                .centerCrop()
                .into(movieImage)

        }

        override fun onClick(p0: View?) {
            val movie = movies[adapterPosition]
            val intent = Intent(context, DetailMovie::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

}