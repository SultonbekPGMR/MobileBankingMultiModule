package uz.gita.entity.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.common.model.UserData


/**
 * Created by Sultonbek Tulanov on 08/01/2025.
 */

internal interface HomeApi {

    @GET("home/user-info/details")
    suspend fun getUserFullInfo(): Response<UserData>


}