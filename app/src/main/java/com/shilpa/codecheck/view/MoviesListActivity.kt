package com.shilpa.codecheck.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.shilpa.codecheck.R
import com.shilpa.codecheck.repository.model.MovieInfo
import com.shilpa.codecheck.repository.network.MovieClientService
import com.shilpa.codecheck.viewmodel.MoviesListViewModel
import com.shilpa.codecheck.viewmodel.MoviesListViewModelFactory

import com.shilpa.codecheck.common.Constants.MOVIE_INFO_PARCELABLE

class MoviesListActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var moviesListViewModel: MoviesListViewModel? = null
    private var moviesListViewModelFactory: MoviesListViewModelFactory? = null
    private var moviesListAdapter: MoviesListAdapter? = null
    private lateinit var movieClientService: MovieClientService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        mRecyclerView = findViewById(R.id.movies_list_recyler_view)

        movieClientService = MovieClientService()
        moviesListViewModelFactory = MoviesListViewModelFactory(application, movieClientService)
        moviesListViewModel = ViewModelProviders.of(this@MoviesListActivity, moviesListViewModelFactory).get(MoviesListViewModel::class.java!!)

        moviesListViewModel!!.movies.observe(this@MoviesListActivity, Observer { movieInfosList ->
            if (movieInfosList!!.size > 0)
                loadMoviesList(movieInfosList)
        })

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun loadMoviesList(movieInfosList: List<MovieInfo>?) {

        val mLayoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        moviesListAdapter = MoviesListAdapter(this, movieInfosList)
        mRecyclerView!!.adapter = moviesListAdapter
    }

    fun handleItemClick(movieInfo: MovieInfo) {
        val intent = Intent(this, MoviesDetailActivity::class.java)
        intent.putExtra(MOVIE_INFO_PARCELABLE, movieInfo)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
