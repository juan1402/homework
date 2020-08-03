package com.sweatworks.data.repository.user

import com.sweatworks.data.database.dao.FavoriteDao
import com.sweatworks.data.database.dao.UserInfoDao
import com.sweatworks.data.database.model.UserInfoEntity
import com.sweatworks.data.networking.RandomUserApi
import com.sweatworks.data.networking.base.getData
import com.sweatworks.data.networking.model.UserResponse
import com.sweatworks.data.repository.base.BaseRepository
import com.sweatworks.domain.model.User
import com.sweatworks.domain.repository.UserInfoRepository

class UserInfoRepositoryImpl(
    private val userApi: RandomUserApi,
    private val userInfoDao: UserInfoDao
) : BaseRepository<List<User>, UserInfoEntity>(), UserInfoRepository {

    override suspend fun getCachedUserData(pageNumber: Int) =
        fetchDbData { userInfoDao.getUserInformation(pageNumber) }

    override suspend fun getUserData(pageNumber: Int) = fetchListData(
        apiDataProvider = {
            userApi.getUserInfo(pageNumber, RESULTS_PER_PAGE).getData(
                fetchFromCacheAction = { userInfoDao.getUserInformation(pageNumber) },
                cacheAction = { userInfoDao.saveUserInformation(it) })
        },
        dbDataProvider = { userInfoDao.getUserInformation(pageNumber) }
    )

    override suspend fun searchUsers(query: String) =
        fetchDbData {
            UserInfoEntity(Int.MAX_VALUE, ArrayList<UserResponse>()
                .apply {
                    userInfoDao.getResults().forEach { addAll(it.results) }
                }.filter {
                    it.nameResponse.firstName.contains(query, true)
                })
        }

    companion object {
        private const val RESULTS_PER_PAGE = 50
    }
}