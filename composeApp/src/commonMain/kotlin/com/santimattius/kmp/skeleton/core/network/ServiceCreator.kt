package com.santimattius.kmp.skeleton.core.network

import com.santimattius.kmp.skeleton.core.data.Picture
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ServiceCreator(baseUrl: String) {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }
    private val ktorfit = Ktorfit.Builder()
        .baseUrl(baseUrl)
        .httpClient(client)
        .build()

    fun createPictureService() = ktorfit.create<PictureService>()
}

interface PictureService {

    @GET("random")
    suspend fun random(): Picture
}