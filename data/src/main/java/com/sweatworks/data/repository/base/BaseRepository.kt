package com.sweatworks.data.repository.base

import com.sweatworks.data.common.coroutine.CoroutineContextProvider
import com.sweatworks.data.common.utils.Connectivity
import com.sweatworks.data.database.DB_ENTRY_ERROR
import com.sweatworks.data.networking.base.DomainMapper
import com.sweatworks.domain.model.Failure
import com.sweatworks.domain.model.HttpError
import com.sweatworks.domain.model.Result
import com.sweatworks.domain.model.Success
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchListData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R?
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) { apiDataProvider() }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel())
                else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     * Use this when communicating only with the db service
     */
    protected suspend fun fetchDbData(dbDataProvider: suspend () -> R?): Result<T> {
        return withContext(contextProvider.io) {
            val dbResult = dbDataProvider()
            if (dbResult != null) Success(dbResult.mapToDomainModel())
            else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
        }
    }

    /**
     * Use this when communicating only with the db service
     * and no mapper is being used
     */
    protected suspend fun fetchAny(dbDataProvider: suspend () -> T?): Result<T> {
        return withContext(contextProvider.io) {
            val dbResult = dbDataProvider()
            if (dbResult != null) Success(dbResult)
            else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
        }
    }
}