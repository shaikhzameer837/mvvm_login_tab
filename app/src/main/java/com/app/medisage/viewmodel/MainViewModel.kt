package com.app.medisage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.medisage.data.repository.MainRepository
import com.app.medisage.model.ItemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val postList = MutableLiveData<List<ItemModel>>()
    val favList = MutableLiveData<List<ItemModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPostList() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<ItemModel>> {
            override fun onResponse(call: Call<List<ItemModel>>, response: Response<List<ItemModel>>) {
                postList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<ItemModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}