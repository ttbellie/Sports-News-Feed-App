package com.example.sportsnewsfeed.data

import com.example.sportsnewsfeed.R

object DummyData {
    fun getNewsItems(): List<NewsItem> = listOf(
        NewsItem(
            id = 1,
            title = "City FC wins dramatic match in final minute",
            description = "City FC secured an exciting 2-1 victory after scoring in the final minute of the game. The match remained close throughout, but a late goal gave the home team an important win and delighted the fans.",
            imageResId = R.drawable.sports_1,
            category = "Football",
            isFeatured = true
        ),
        NewsItem(
            id = 2,
            title = "Lions prepare for weekend basketball clash",
            description = "The Lions are getting ready for a major basketball game this weekend. The coach said the team has trained well and is focused on improving defence and controlling the pace of the match.",
            imageResId = R.drawable.sports_2,
            category = "Basketball",
            isFeatured = true
        ),
        NewsItem(
            id = 3,
            title = "National cricket team announces new captain",
            description = "The national cricket team has officially introduced its new captain ahead of the upcoming tournament. Fans are hopeful that the leadership change will bring stronger performance and better results.",
            imageResId = R.drawable.sports_3,
            category = "Cricket",
            isFeatured = true
        ),
        NewsItem(
            id = 4,
            title = "Tennis star reaches semifinals after strong comeback",
            description = "A leading tennis player reached the semifinals after recovering from a slow start in the match. The player showed confidence, strong serving, and better movement in the final sets.",
            imageResId = R.drawable.sports_4,
            category = "Tennis",
            isFeatured = false
        ),
        NewsItem(
            id = 5,
            title = "Football coach praises young players after win",
            description = "After the latest win, the football coach praised the younger players for their energy and confidence. He said their performance shows that the club has a bright future ahead.",
            imageResId = R.drawable.sports_5,
            category = "Football",
            isFeatured = false
        ),
        NewsItem(
            id = 6,
            title = "Basketball league sees record attendance this season",
            description = "This season, the basketball league has reported its highest attendance in recent years. Organisers believe the exciting matches and stronger fan engagement have helped increase support.",
            imageResId = R.drawable.sports_6,
            category = "Basketball",
            isFeatured = false
        ),
        NewsItem(
            id = 7,
            title = "Cricket opener scores century in home series",
            description = "The team’s opening batter scored an impressive century during the home series. The innings helped the team build a strong total and created pressure on the visiting side.",
            imageResId = R.drawable.sports_1,
            category = "Cricket",
            isFeatured = false
        ),
        NewsItem(
            id = 8,
            title = "Swimming championship begins with fast results",
            description = "The national swimming championship started strongly, with several athletes recording fast times in the first round. Coaches said the early results are promising for the rest of the event.",
            imageResId = R.drawable.sports_2,
            category = "Swimming",
            isFeatured = false
        ),
        NewsItem(
            id = 9,
            title = "Football derby expected to draw huge crowd",
            description = "This week’s football derby is expected to attract a huge crowd as two rival teams prepare to meet. Security and transport plans have been increased due to the expected number of supporters.",
            imageResId = R.drawable.sports_3,
            category = "Football",
            isFeatured = true
        ),
        NewsItem(
            id = 10,
            title = "Basketball captain returns after ankle injury",
            description = "The basketball team captain is set to return after missing several matches with an ankle injury. Team staff said the player has recovered well and is ready for limited minutes.",
            imageResId = R.drawable.sports_4,
            category = "Basketball",
            isFeatured = false
        ),
        NewsItem(
            id = 11,
            title = "Cricket fans excited for upcoming international series",
            description = "Cricket supporters are looking forward to the upcoming international series, which is expected to bring highly competitive matches. Tickets have already sold quickly in several cities.",
            imageResId = R.drawable.sports_5,
            category = "Cricket",
            isFeatured = true
        ),
        NewsItem(
            id = 12,
            title = "Tennis tournament schedule released for next month",
            description = "Organisers have released the full schedule for next month’s tennis tournament. The event will include top players, qualifying rounds, and several evening matches for fans.",
            imageResId = R.drawable.sports_6,
            category = "Tennis",
            isFeatured = false
        )
    )

    fun getFeaturedItems(): List<NewsItem> = getNewsItems().filter { it.isFeatured }

    fun getNewsByCategory(category: String): List<NewsItem> {
        return if (category == "All") getNewsItems()
        else getNewsItems().filter { it.category == category }
    }

    fun getRelatedStories(currentItem: NewsItem): List<NewsItem> {
        return getNewsItems().filter { it.category == currentItem.category && it.id != currentItem.id }
    }

    fun getNewsById(id: Int): NewsItem? = getNewsItems().find { it.id == id }

    fun getCategories(): List<String> = listOf("All", "Football", "Basketball", "Cricket", "Tennis", "Baseball")
}
