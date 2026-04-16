package com.example.sportsnewsfeed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeed.MainActivity
import com.example.sportsnewsfeed.R
import com.example.sportsnewsfeed.adapters.RelatedStoriesAdapter
import com.example.sportsnewsfeed.data.BookmarkManager
import com.example.sportsnewsfeed.data.DummyData

class DetailFragment : Fragment() {

    companion object {
        private const val ARG_NEWS_ID = "news_id"
        fun newInstance(newsId: Int): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply { putInt(ARG_NEWS_ID, newsId) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsId = arguments?.getInt(ARG_NEWS_ID) ?: return
        val newsItem = DummyData.getNewsById(newsId) ?: return

        val ivImage = view.findViewById<ImageView>(R.id.iv_detail_image)
        val tvTitle = view.findViewById<TextView>(R.id.tv_detail_title)
        val tvCategory = view.findViewById<TextView>(R.id.tv_detail_category)
        val tvDescription = view.findViewById<TextView>(R.id.tv_detail_description)
        val btnBookmark = view.findViewById<ImageButton>(R.id.btn_bookmark)
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        val rvRelated = view.findViewById<RecyclerView>(R.id.rv_related)

        ivImage.setImageResource(newsItem.imageResId)
        tvTitle.text = newsItem.title
        tvCategory.text = newsItem.category
        tvDescription.text = newsItem.description

        updateBookmarkIcon(btnBookmark, newsItem.id)
        btnBookmark.setOnClickListener {
            val isNowBookmarked = BookmarkManager.toggleBookmark(requireContext(), newsItem.id)
            updateBookmarkIcon(btnBookmark, newsItem.id)
            val msg = if (isNowBookmarked) "Bookmarked!" else "Removed from bookmarks"
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val relatedItems = DummyData.getRelatedStories(newsItem)
        val relatedAdapter = RelatedStoriesAdapter(relatedItems) { item ->
            (activity as? MainActivity)?.openDetail(item.id)
        }
        rvRelated.layoutManager = LinearLayoutManager(context)
        rvRelated.isNestedScrollingEnabled = false
        rvRelated.adapter = relatedAdapter
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.showBottomNav(false)
    }

    private fun updateBookmarkIcon(btn: ImageButton, id: Int) {
        if (BookmarkManager.isBookmarked(requireContext(), id)) {
            btn.setImageResource(R.drawable.ic_bookmark_filled)
        } else {
            btn.setImageResource(R.drawable.ic_bookmark_border)
        }
    }
}
