package com.joshskeen.android.kotlintodolist

import com.joshskeen.android.kotlintodolist.model.DataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TodoApplicationModule {

    @Provides
    @Singleton
    fun providesDataStore(): DataStore {
        return DataStore("test")
    }

}