package com.example.listapp

import com.example.listapp.data.Hero
import com.example.listapp.data.HeroItem
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    fun getHeroes(): Single<Hero>
}