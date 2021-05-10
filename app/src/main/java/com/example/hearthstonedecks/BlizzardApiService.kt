package com.example.hearthstonedecks

import com.example.hearthstonedecks.data.AccessData
import com.example.hearthstonedecks.data.Card
import com.example.hearthstonedecks.data.CardResponse
import com.example.hearthstonedecks.data.Deck
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://us.api.blizzard.com/hearthstone/"
private const val BASE_AUTH_URL = "https://eu.battle.net/"
private const val CLIENT_ID = "8a652978c8fd447e922e8ed1ec3c7170"
private const val CLIENT_SECRET = "3Nr9Ifa7V8msMosfmMp3CQtsYmOTrNnv"
private const val LOCALE = "en_US"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val retrofitAuth = Retrofit.Builder()
    .baseUrl(BASE_AUTH_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface BlizzardAuthService {
    @POST("oauth/token")
    suspend fun getToken(
        @Query("client_id")
        clientId : String = CLIENT_ID,
        @Query("client_secret")
        clientSecret : String = CLIENT_SECRET,
        @Query("grant_type")
        clientCredentials : String = "client_credentials",
        @Query("scope")
        scope : String = "openid"
    ) : AccessData
}

interface BlizzardApiService {
    @GET("cards/")
    suspend fun getCards(
        @Query("access_token")
        accessToken : String,
        @Query("locale")
        locale : String = LOCALE
    ) : CardResponse

    @GET("cards/{slug}")
    suspend fun getCard(
        @Path("slug")
        slug : String,
        @Query("access_token")
        accessToken : String,
        @Query("locale")
        locale : String = LOCALE
    ) : Card

    @GET("deck/")
    suspend fun getDeck(
        @Query("access_token")
        accessToken : String,
        @Query("code")
        code : String,
        @Query("locale")
        locale : String = LOCALE
    ) : Deck
}

object BlizzardAuth {
    val retrofitAuthService : BlizzardAuthService by lazy {
        retrofitAuth.create(BlizzardAuthService::class.java)
    }
}

object BlizzardApi {
    val retrofitService : BlizzardApiService by lazy {
        retrofit.create(BlizzardApiService::class.java)
    }
}