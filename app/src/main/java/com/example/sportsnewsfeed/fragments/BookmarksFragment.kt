package com.example.sportsnewsfeed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeed.MainActivity
import com.example.sportsnewsfeed.R
import com.example.sportsnewsfeed.adapters.NewsAdapter
import com.example.sportsnewsfeed.data.BookmarkManager

class BookmarksFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var tvEmpty: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvBookmarks = view.findViewById<RecyclerView>(R.id.rv_bookmarks)
        tvEmpty = view.findViewById(R.id.tv_empty_bookmarks)

        adapter = NewsAdapter(emptyList()) { item ->
            (activity as? MainActivity)?.openDetail(item.id)
        }
        rvBookmarks.layoutManager = LinearLayoutManager(context)
        rvBookmarks.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.showBottomNav(true)
        refreshBookmarks()
    }

    private fun refreshBookmarks() {
        val bookmarked = BookmarkManager.getBookmarkedItems(requireContext())
        adapter.updateItems(bookmarked)
        tvEmpty.visibility = if (bookmarked.isEmpty()) View.VISIBLE else View.GONE
    }
}
