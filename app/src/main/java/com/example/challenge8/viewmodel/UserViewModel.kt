package com.example.challenge8.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge8.model.GetAllUserItem
import com.example.challenge8.model.ResponseLogin
import com.example.challenge8.model.ResponseRegister
import com.example.challenge8.network.APIServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class UserViewModel @Inject constructor(@Named("UserApiService")api : APIServices) : ViewModel(){
    private val userstate = MutableStateFlow(emptyList<GetAllUserItem>())
    val datauserstate: StateFlow<List<GetAllUserItem>> get() = userstate
    private val api = api
//    lateinit var livedataUpdate : MutableLiveData<ResponseUpdate>

    init {
        viewModelScope.launch {
            val dataUser = api.getAllUser()
            userstate.value = dataUser
        }
    }

//    fun getUserLiveDataObserver(): MutableLiveData<List<GetAllUserItem>> {
//        return livedataUser
//    }
//
//    fun userLogin2(){
//
//    }
//    fun userLogin(){
//        ApiClient.instance.getAllUser()
//            .enqueue(object : Callback<List<GetAllUserItem>> {
//                override fun onResponse(
//                    call: Call<List<GetAllUserItem>>,
//                    response: Response<List<GetAllUserItem>>
//                ) {
//                    if (response.isSuccessful){
//                        livedataUser.postValue(response.body())
//                    }else{
//                        livedataUser.postValue(null)
//                    }
//                }
//
//                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
//                    livedataUser.postValue(null)
//                }
//
//            })
//    }
//
    fun userRegister(requestUser : ResponseLogin) : Boolean{
        var messageResponse = false
        api.addNewUser(requestUser)
            .enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    messageResponse = response.isSuccessful
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {

                }

            })
        return messageResponse
    }
}