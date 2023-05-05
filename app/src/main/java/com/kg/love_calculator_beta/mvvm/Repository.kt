package com.kg.love_calculator_beta.mvvm

import androidx.lifecycle.MutableLiveData
import com.kg.love_calculator_beta.remote.LoveApi
import com.kg.love_calculator_beta.remote.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val api: LoveApi) {
    fun getLove(fName:String, sName:String): MutableLiveData<LoveModel> {
        val liveLove = MutableLiveData<LoveModel>()

        api.percentageName(fName, sName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if(response.isSuccessful){
                    liveLove.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<LoveModel>, t: Throwable) {

            }
        })
        return liveLove
    }
}