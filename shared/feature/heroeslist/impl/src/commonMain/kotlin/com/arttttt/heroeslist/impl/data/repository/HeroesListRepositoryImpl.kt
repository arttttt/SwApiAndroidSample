package com.arttttt.heroeslist.impl.data.repository

import com.arttttt.heroeslist.impl.data.network.api.HeroesListApi
import com.arttttt.heroeslist.impl.data.network.model.HeroesApiResponse
import com.arttttt.heroeslist.api.Hero
import com.arttttt.heroeslist.impl.domain.repository.HeroesListRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class HeroesListRepositoryImpl(
    //private val heroesListApi: HeroesListApi
) : HeroesListRepository {

    /**
     * todo: pass [HeroesListApi] through the constructor
     */
    private val heroesListApi: HeroesListApi

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    }
                )
            }

            defaultRequest {
                url("https://www.swapi.tech/api//")
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }

        heroesListApi = object : HeroesListApi {

            override suspend fun loadHeroesPage(page: Int, limit: Int): HeroesApiResponse {
                return httpClient
                    .get {
                        url {
                            path("people")
                        }

                        parameter("page", page.toString())
                        parameter("limit", limit.toString())
                    }
                    .body()
            }
        }
    }

    override suspend fun loadHeroes(page: Int): List<Hero> {
        val response = heroesListApi.loadHeroesPage(page, 10)

        return response.results.map { hero ->
            Hero(
                id = hero.id,
                name = hero.name
            )
        }
    }
}
