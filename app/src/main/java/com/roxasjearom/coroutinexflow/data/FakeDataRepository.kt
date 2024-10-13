package com.roxasjearom.coroutinexflow.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeDataRepository @Inject constructor() : DataRepository {

    override suspend fun fetchSmallData(): String {
        delay(1000)
        return "Small Data"
    }

    override suspend fun fetchMediumData(): String {
        delay(2000)
        return "Medium Data"
    }

    override suspend fun fetchBigData(): String {
        delay(3000)
        return "BigData"
    }
}
