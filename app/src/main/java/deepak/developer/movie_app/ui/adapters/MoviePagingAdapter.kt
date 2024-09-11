package deepak.developer.movie_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import deepak.developer.movie_app.data.models.Movie
import deepak.developer.movie_app.databinding.ViewHolderMovieBinding

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(DIFF_UTIL) {


    private var onCLick: ((String) -> Unit)? = null

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun onMovieClick(listener: (String) -> Unit) {
        onCLick = listener
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)

        holder.viewDataBinding.movie = data

        holder.viewDataBinding.root.setOnClickListener {
            onCLick?.let {
                it(data?.imdbID!!)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}