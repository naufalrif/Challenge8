package com.example.challenge8.network

import com.example.challenge8.model.GetAllUserItem
import com.example.challenge8.model.MovieResponse
import com.example.challenge8.model.ResponseLogin
import com.example.challenge8.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface APIServices {
    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    //user
    @GET("datauserlogin")
    suspend fun getAllUser(): List<GetAllUserItem>

    @POST("datauserlogin")
    fun addNewUser(@Body requestUser: ResponseLogin): Call<ResponseRegister>
//    @GET("movies")
//    fun getAllFilm() : List<GetAllFilmItem>
//
////    @POST("user")
////    @FormUrlEncoded
////    fun userPostRegister(
////        @Field("username")username : String,
////        @Field("email")email : String,
////        @Field("password")password : String
////    ) : Call<GetAllUserItem>
////
////    @POST("user")
////    @FormUrlEncoded
////    fun loginUser(
////        @Field("email") email : String,
////        @Field("password") password : String
////    ) : Call<ResponseLogin>
//
//    @GET("user")
//    fun getAllUser() : List<GetAllUserItem>
//
//    @GET("user")
//    fun getUser(
//        @Query("username")username : String
//    ) : Call<List<GetAllUserItem>>
//
//    @PUT("user/{id}")
//    @FormUrlEncoded
//    fun updateUser(
//        @Path("id")id : String,
//        @Field("username")username : String,
//        @Field("email")email: String,
//        @Field("password")password: String
//    ) : Call<ResponseRegister>
//
//    @POST("user")
//    fun postUser(@Body reqUser : ResponseRegister) : Call<GetAllUserItem>
}