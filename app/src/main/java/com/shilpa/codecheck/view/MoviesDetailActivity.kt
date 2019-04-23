package com.shilpa.codecheck.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.shilpa.codecheck.R
import com.shilpa.codecheck.common.Constants
import com.shilpa.codecheck.repository.model.MovieInfo
import com.shilpa.codecheck.common.Constants.MOVIE_INFO_PARCELABLE

internal class MoviesDetailActivity : AppCompatActivity() {
    private var mMovieDesc: TextView? = null
    private var mMovieDirector: TextView? = null
    private var mMoviewReleaseDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        mMovieDesc = findViewById(R.id.movie_desc)
        mMovieDirector = findViewById(R.id.movie_director)
        mMoviewReleaseDate = findViewById(R.id.movie_releaseDate)

        mMovieInfo = MovieInfo()

        val intent = intent
        if (intent != null) {
            mMovieInfo = intent.getParcelableExtra(MOVIE_INFO_PARCELABLE)
            loadMoviesDetail(mMovieInfo)

        }
    }

    private fun loadMoviesDetail(mMovieInfo: MovieInfo?) {

        if (mMovieInfo != null) {
            mMovieDesc!!.text = Constants.MOVIE_DESC + mMovieInfo.description!!
            mMovieDirector!!.text = Constants.MOVIE_DIRECTOR + mMovieInfo.director!!
            mMoviewReleaseDate!!.text = Constants.MOVIE_RELEASE_DATE + mMovieInfo.releaseDate!!
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

        private var mMovieInfo: MovieInfo? = null
    }
}
