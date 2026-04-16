# Sports News Feed App

## Overview
Sports News Feed App is an Android application developed for **SIT305 Task 5.1C**. The purpose of this app is to demonstrate the use of **RecyclerView**, **Fragments**, and **Single Activity Architecture** in Android development.  

The application displays sports news in a simple and user-friendly interface. Users can browse featured matches, read the latest sports news, open a detail screen for each story, filter news by category, and save favourite stories using bookmarks.

## Features
- Single Activity Architecture
- Fragment-based navigation
- Horizontal RecyclerView for **Featured Matches**
- Vertical RecyclerView for **Latest Sports News**
- Detail screen with:
  - large image
  - title
  - description
  - related stories
- Search/filter news by sport category
- Bookmark favourite stories locally
- Bookmark screen to view saved stories later
- Hardcoded dummy news data for testing

## Technologies Used
- **Kotlin**
- **Android Studio**
- **RecyclerView**
- **Fragments**
- **SharedPreferences** for bookmark storage
- **Material Design components**

## App Structure
The app follows a simple fragment-based structure:

- `MainActivity`  
  Hosts all fragments using a single activity design.

- `HomeFragment`  
  Displays featured matches and latest sports news.

- `DetailFragment`  
  Shows full details of the selected news story and related stories.

- `BookmarksFragment`  
  Displays all bookmarked stories saved by the user.

## Data Model
The app uses a simple `NewsItem` data class:

```kotlin
data class NewsItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int,
    val category: String,
    val isFeatured: Boolean = false
)
