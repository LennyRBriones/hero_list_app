package com.example.herolistapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.RowId

interface ApiService {

    @GET("/api/2712892742210687/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String):Response<SuperHeroDataResponse>

    @GET("/api/2712892742210687/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superheroId:String):Response<SuperHeroDetailResponse>
}