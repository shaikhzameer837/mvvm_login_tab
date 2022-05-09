package com.app.medisage.data.repository

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}