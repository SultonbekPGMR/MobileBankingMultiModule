package uz.gita.entity.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.gita.common.model.TransactionData
import uz.gita.entity.remote.api.TransferApi


/**
 * Created by Sultonbek Tulanov on 05/02/2025.
 */

class MonitoringDataSource internal constructor(
    private val transferApi: TransferApi,
) : PagingSource<Int, TransactionData>() {
    override fun getRefreshKey(state: PagingState<Int, TransactionData>): Int {
        return state.anchorPosition?.inc() ?: 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransactionData> {
        try {
            val response = transferApi.getHistory(params.loadSize, params.key ?: 0)
            if (response.isSuccessful) {
                return LoadResult.Page(
                    data = response.body()?.transactions ?: emptyList(),
                    prevKey = if (params.key == 0 || params.key == null) null else params.key!! - 1,
                    nextKey = if (response.body()!!.currentPage < (response.body()?.totalPages
                            ?: 0)
                    ) response.body()!!.currentPage + 1 else null
                )
            }
            return LoadResult.Error(Exception(response.errorBody()?.string()))
        } catch (e: Throwable) {
            e.printStackTrace()

            return LoadResult.Error(Exception(e))
        }
    }

}