package uz.gita.entity.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.gita.entity.model.request.NewCardRequest
import uz.gita.entity.model.response.CardDataResponse
import uz.gita.entity.model.response.SingleResponse


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal interface CardApi {

    @POST("card")
    suspend fun addCard(@Body newCardData: NewCardRequest): Response<SingleResponse>


    @GET("card")
    suspend fun getAllCards(): Response<List<CardDataResponse>>


}

