package com.example.test.utils

object Utils {
    fun getIdCategory(category: String?): Int {
        var id = 0
        id = when (category) {
            "Food & Cooking" -> 1
            "Fashion" -> 2
            "Entertaiment" -> 3
            "Travel" -> 4
            "Beauty" -> 5
            "Healthcare" -> 6
            "Technology" -> 7
            "Automotive" -> 8
            "Education" -> 9
            "Sports" -> 10
            "Finance" -> 11
            "Career" -> 12
            else -> 0
        }
        return id
    }
}