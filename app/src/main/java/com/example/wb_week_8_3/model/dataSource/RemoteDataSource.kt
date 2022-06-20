package com.example.wb_week_8_3.model.dataSource



import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.data.FavoriteModel
import com.example.wb_week_8_3.model.data.VoteModel
import com.example.wb_week_8_3.utils.convertFavoriteModelToCatModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

const val API_KEY = "eb401d21-2756-4df4-9ca8-144ba151a3d1"
const val API_BASE_URL = "https://api.thecatapi.com/v1/"
const val CAT_IMAGE_URL = "images/search"
const val FAVORITE_CATS_URL = "favourites/"

class RemoteDataSource {
    private val clientApi = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getRandomImageCat(): CatModel {
        return try {
            val dataCat: List<CatModel> = clientApi.get(API_BASE_URL + CAT_IMAGE_URL) {
                header("x-api-key", API_KEY)
                contentType(ContentType.Application.Json)
            }
            dataCat[0]
        } catch (e: Exception) {
            CatModel()
        }
    }

    suspend fun getAllFavoriteCatsFromServer(): List<CatModel> {
        return try {
            val data: List<FavoriteModel> = clientApi.get((API_BASE_URL + FAVORITE_CATS_URL)) {
                header("x-api-key", API_KEY)
                contentType(ContentType.Application.Json)
            }
             convertFavoriteModelToCatModel(data)
        } catch (e: Exception){
            emptyList()
        }
    }

    suspend fun postLikeToServer(postRequest: VoteModel) {
         try {
            clientApi.post<HttpResponse>(API_BASE_URL + FAVORITE_CATS_URL) {
                header("x-api-key", API_KEY)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e:java.lang.Exception){
        }
    }
}
