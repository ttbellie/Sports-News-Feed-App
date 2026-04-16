package com.example.sportsnewsfeed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnewsfeed.MainActivity
import com.example.sportsnewsfeed.R
import com.example.sportsnewsfeed.adapters.FeaturedMatchAdapter
import com.example.sportsnewsfeed.adapters.NewsAdapter
import com.example.sportsnewsfeed.data.DummyData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class HomeFragment : Fragment() {

    private lateinit var featuredAdapter: FeaturedMatchAdapter
    private lateinit var newsAdapter: NewsAdapter
    private var selectedCategory = "All"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChips(view)
        setupFeaturedRecyclerView(view)
        setupNewsRecyclerView(view)
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.showBottomNav(true)
    }

    private fun setupChips(view: View) {
        val chipGroup = view.findViewById<ChipGroup>(R.id.chip_group_categories)
        chipGroup.removeAllViews()
        DummyData.getCategories().forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category
                isCheckable = true
                isChecked = category == selectedCategory
                setOnClickListener {
                    selectedCategory = category
                    filterData()
                }
            }
            chipGroup.addView(chip)
        }
    }

    private fun setupFeaturedRecyclerView(view: View) {
        val rvFeatured = view.findViewById<RecyclerView>(R.id.rv_featured)
        featuredAdapter = FeaturedMatchAdapter(DummyData.getFeaturedItems()) { item ->
            (activity as? MainActivity)?.openDetail(item.id)
        }
        rvFeatured.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvFeatured.adapter = featuredAdapter
    }

    private fun setupNewsRecyclerView(view: View) {
        val rvNews = view.findViewById<RecyclerView>(R.id.rv_news)
        newsAdapter = NewsAdapter(DummyData.getNewsItems()) { item ->
            (activity as? MainActivity)?.openDetail(item.id)
        }
        rvNews.layoutManager = LinearLayoutManager(context)
        rvNews.isNestedScrollingEnabled = false
        rvNews.adapter = newsAdapter
    }

    private fun filterData() {
        val filtered = DummyData.getNewsByCategory(selectedCategory)
        newsAdapter.updateItems(filtered)
        if (selectedCategory == "All") {
            featuredAdapter.updateItems(DummyData.getFeaturedItems())
        } else {
            featuredAdapter.updateItems(filtered.filter { it.isFeatured })
        }
    }
}
