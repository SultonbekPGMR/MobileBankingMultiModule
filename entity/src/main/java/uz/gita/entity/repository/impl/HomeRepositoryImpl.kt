package uz.gita.entity.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.util.handleApiCall
import uz.gita.common.model.UserData
import uz.gita.entity.remote.api.HomeApi
import uz.gita.entity.repository.HomeRepository
import javax.inject.Inject


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

internal class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi,

    ) : HomeRepository {
    override fun getUserInfo(): Flow<Result<UserData>> = handleApiCall(
        apiCall = { homeApi.getUserFullInfo() },
        onSuccess = { it }
    )


}