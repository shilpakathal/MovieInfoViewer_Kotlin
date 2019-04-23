package com.shilpa.codecheck.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import com.shilpa.codecheck.R
import com.shilpa.codecheck.repository.model.MovieInfo
import com.shilpa.codecheck.view.util.FavouriteMoviesUtil

import com.shilpa.codecheck.common.Constants.MOVIE_RATING
import com.shilpa.codecheck.common.Constants.MOVIE_RELEASE_DATE

class MoviesListAdapter(private val mParentActivity: MoviesListActivity, private val moviesList: List<MovieInfo>?) : RecyclerView.Adapter<MoviesListAdapter.MoviesListViewholder>() {
    private val mContext: Context
    private val mFavouriteMoviesUtil: FavouriteMoviesUtil

    private val mOnItemClickListner = View.OnClickListener { v -> mParentActivity.handleItemClick(v.tag as MovieInfo) }

    init {
        this.mContext = mParentActivity.applicationContext
        mFavouriteMoviesUtil = FavouriteMoviesUtil(mContext)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MoviesListViewholder {
        val itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movies_list_row_item, viewGroup, false)
        return MoviesListViewholder(itemView)
    }

    override fun onBindViewHolder(moviesListView: MoviesListViewholder, i: Int) {
        val movieInfo = moviesList?.get(i)

        if (movieInfo != null) {

            moviesListView.movieTitle.text = movieInfo.title
            moviesListView.releaseDate.text = MOVIE_RELEASE_DATE + movieInfo.releaseDate!!
            moviesListView.rating.text = MOVIE_RATING + movieInfo.rtScore!!

            if (isFavouriteMovie(movieInfo.id)) {
                //Red heart
                moviesListView.favouriteBtn.tag = "red"
                moviesListView.favouriteBtn.setImageDrawable(mContext.getDrawable(R.drawable.ic_favorite))

            } else {
                // grey heart
                moviesListView.favouriteBtn.tag = "grey"
                moviesListView.favouriteBtn.setImageDrawable(mContext.getDrawable(R.drawable.ic_favorite_grey))

            }
            moviesListView.itemView.tag = movieInfo
            moviesListView.itemView.setOnClickListener(mOnItemClickListner)
        }
    }

    private fun isFavouriteMovie(movieId: String?): Boolean {

        return if (mFavouriteMoviesUtil.CheckFavouriteMoviesFromSp(movieId))
            true
        else
            false
    }

    override fun getItemCount(): Int {
        return moviesList?.size?:0
    }

    inner class MoviesListViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val movieTitle: TextView
         val releaseDate: TextView
         val rating: TextView
         val favouriteBtn: ImageButton

        init {
            movieTitle = itemView.findViewById(R.id.movie_title)
            releaseDate = itemView.findViewById(R.id.release_date)
            rating = itemView.findViewById(R.id.rating)
            favouriteBtn = itemView.findViewById(R.id.favourites)

//            @ViewById(R.id.movie_title)
//            lateinit var movieTitle: TextView

            favouriteBtn.setOnClickListener {
                if (favouriteBtn.tag == "grey") {
                    favouriteBtn.tag = "red"
                    favouriteBtn.setImageDrawable(mContext.getDrawable(R.drawable.ic_favorite))
                    //save in Shared preference
                    mFavouriteMoviesUtil.saveFavouriteMoviesToSp(this@MoviesListAdapter.moviesList!![adapterPosition].id!!)

                } else if (favouriteBtn.tag == "red") {
                    favouriteBtn.tag = "grey"
                    favouriteBtn.setImageDrawable(mContext.getDrawable(R.drawable.ic_favorite_grey))
                    //remove from Shared preference
                    mFavouriteMoviesUtil.RemoveFavouriteMoviesFromSP(this@MoviesListAdapter.moviesList!![adapterPosition].id!!)
                }
            }

        }
    }
}
