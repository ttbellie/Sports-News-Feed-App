package com.example.sportsnewsfeed.data

import android.content.Context
import android.content.SharedPreferences

object BookmarkManager {
    private const val PREF_NAME = "bookmarks_pref"
    private const val KEY_BOOKMARKS = "bookmarked_ids"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun addBookmark(context: Context, id: Int) {
        val ids = getBookmarkedIds(context).toMutableSet()
        ids.add(id.toString())
        getPrefs(context).edit().putStringSet(KEY_BOOKMARKS, ids).apply()
    }

    fun removeBookmark(context: Context, id: Int) {
        val ids = getBookmarkedIds(context).toMutableSet()
        ids.remove(id.toString())
        getPrefs(context).edit().putStringSet(KEY_BOOKMARKS, ids).apply()
    }

    fun isBookmarked(context: Context, id: Int): Boolean {
        return getBookmarkedIds(context).contains(id.toString())
    }

    fun toggleBookmark(context: Context, id: Int): Boolean {
        return if (isBookmarked(context, id)) {
            removeBookmark(context, id)
            false
        } else {
            addBookmark(context, id)
            true
        }
    }

    fun getBookmarkedIds(context: Context): Set<String> {
        return getPrefs(context).getStringSet(KEY_BOOKMARKS, emptySet()) ?: emptySet()
    }

    fun getBookmarkedItems(context: Context): List<NewsItem> {
        val ids = getBookmarkedIds(context)
        return DummyData.getNewsItems().filter { ids.contains(it.id.toString()) }
    }
}
