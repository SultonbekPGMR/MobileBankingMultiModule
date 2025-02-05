package uz.gita.entity.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.gita.common.model.TransactionData
import uz.gita.entity.model.request.*
import uz.gita.entity.model.response.HistoryResponse
import uz.gita.entity.model.response.SingleResponse
import uz.gita.entity.model.response.VerifyToken


/**
 * Created by Sultonbek Tulanov on 09/01/2025.
 */

internal interface TransferApi {

    @POST("transfer/card-owner")
    suspend fun getOwnerNameByCardPan(@Body pan: CardPan): Response<CardPan>

    @POST("transfer/fee")
    suspend fun getCalculatedFee(@Body calculateFeeRequest: CalculateFeeRequest): Response<String>


    @POST("transfer/transfer")
    suspend fun transfer(@Body transferRequest: TransferRequest): Response<VerifyToken>


    @POST("transfer/transfer/verify")
    suspend fun verify(@Body code: VerifyRequest): Response<SingleResponse>

    @POST("transfer/transfer/resend")
    suspend fun resendSignUpCode(@Body token: ResendCodeRequest): Response<VerifyToken>

    @GET("transfer/history")
    suspend fun getHistory(
        @Query("size") size: Int,
        @Query("current-page") currentPage: Int,
    ): Response<HistoryResponse>

    @GET("home/last-transfers")
    suspend fun getLastTransfers(
    ): Response<List<TransactionData>>



}