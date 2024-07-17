package il.ac.hit.android_movies_info_app.ui.favorites.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import il.ac.hit.android_movies_info_app.data.model.favorite_movie.FavoriteMovie
import il.ac.hit.android_movies_info_app.databinding.ItemMovieBinding
import il.ac.hit.android_movies_info_app.utils.Constants



class FavoritesAdapter(private val listener : MoviesItemListener) :
    RecyclerView.Adapter<FavoritesAdapter.MovieViewHolder>() {

    private val movies = ArrayList<FavoriteMovie>()

    class MovieViewHolder (private val itemBinding: ItemMovieBinding,
                           private val listener: MoviesItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: FavoriteMovie

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: FavoriteMovie) {

            this.movie = item
            itemBinding.title.text = item.title
            itemBinding.description.text = item.overview
            // can change to other sizes, check Constants.kt
            val imagePath:String = Constants.IMAGE_TYPE_W185 +item.posterPath
            Glide.with(itemBinding.root).load(imagePath).into(itemBinding.image)
        }
        override fun onClick(v: View?) {

            listener.onMovieClick(movie.id)
        }
    }

    fun setMovies(movies : Collection<FavoriteMovie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding,listener)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movies[position])

    interface MoviesItemListener {
        fun onMovieClick(movieId :Int)
    }
}