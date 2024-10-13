package com.roxasjearom.coroutinexflow.data

interface DataRepository {

    suspend fun fetchSmallData(): String

    suspend fun fetchMediumData(): String

    suspend fun fetchBigData(): String

}
