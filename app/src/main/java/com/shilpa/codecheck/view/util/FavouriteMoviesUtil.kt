package com.shilpa.codecheck.view.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import java.util.HashSet

class FavouriteMoviesUtil(private val context: Context) {
    private var sharedpreferences: SharedPreferences? = null
    private var favouriteMovieIds: MutableSet<String>? = null

    fun saveFavouriteMoviesToSp(movieId: String) {

        sharedpreferences = context.getSharedPreferences(FAVOURITES_SP_FILE, Context.MODE_PRIVATE)
        val editor = sharedpreferences!!.edit()

        favouriteMovieIds = sharedpreferences!!.getStringSet(FAVOURITE_MOVIES_SP, null)

        if (favouriteMovieIds == null) {
            favouriteMovieIds = HashSet()
        } else {
            favouriteMovieIds = HashSet(favouriteMovieIds!!)
        }
        Log.d("##Writing to SP", movieId)
        favouriteMovieIds!!.add(movieId)
        editor.putStringSet(FAVOURITE_MOVIES_SP, favouriteMovieIds)

        editor.commit()
    }

    fun CheckFavouriteMoviesFromSp(movieId: String?): Boolean {

        sharedpreferences = context.getSharedPreferences(FAVOURITES_SP_FILE, Context.MODE_PRIVATE)

        favouriteMovieIds = sharedpreferences!!.getStringSet(FAVOURITE_MOVIES_SP, null)
        return if (favouriteMovieIds != null) {
            if (favouriteMovieIds!!.contains(movieId)) {
                true
            } else {
                false
            }
        } else false
    }

    fun RemoveFavouriteMoviesFromSP(movieID: String) {

        sharedpreferences = context.getSharedPreferences(FAVOURITES_SP_FILE, Context.MODE_PRIVATE)
        val editor = sharedpreferences!!.edit()

        favouriteMovieIds = sharedpreferences!!.getStringSet(FAVOURITE_MOVIES_SP, null)

        if (favouriteMovieIds != null) {
            favouriteMovieIds = HashSet(favouriteMovieIds!!)
            if (favouriteMovieIds!!.contains(movieID)) {
                favouriteMovieIds!!.remove(movieID)
                editor.putStringSet(FAVOURITE_MOVIES_SP, favouriteMovieIds)
                editor.commit()
            }
        }

    }

    companion object {
        private val LOG_TAG = FavouriteMoviesUtil::class.java!!.getCanonicalName()
        private val FAVOURITES_SP_FILE = "Favourites"
        private val FAVOURITE_MOVIES_SP = "Favourite_movies"
    }
}