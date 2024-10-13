package com.roxasjearom.coroutinexflow.di

import com.roxasjearom.coroutinexflow.data.DataRepository
import com.roxasjearom.coroutinexflow.data.FakeDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideQuestionRepository(impl: FakeDataRepository): DataRepository

}
