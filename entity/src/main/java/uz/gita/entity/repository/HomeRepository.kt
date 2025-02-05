package uz.gita.entity.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.common.model.UserData


/**
 * Created by Sultonbek Tulanov on 12/01/2025.
 */

interface HomeRepository {
    fun getUserInfo(): Flow<Result<UserData>>


}