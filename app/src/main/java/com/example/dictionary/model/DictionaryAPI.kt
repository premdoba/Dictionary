package com.example.dictionary.model

import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPI {
    // website of api is https://dictionaryapi.dev/
    // this get link is provided in the first link end , word is written in {} because it will chage as per user search
    @GET("api/v2/entries/en/{word}")

    suspend fun getWordMeaning(
        @Path("word") word: String
    ): List<DictionaryData>
}