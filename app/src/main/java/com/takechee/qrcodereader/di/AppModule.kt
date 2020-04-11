package com.takechee.qrcodereader.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.takechee.qrcodereader.MainApp
import com.takechee.qrcodereader.data.db.AppDatabase
import com.takechee.qrcodereader.data.db.ReadCodeDatabase
import com.takechee.qrcodereader.data.db.ReadCodeRoomDatabase
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.data.prefs.SharedPreferenceStorage
import com.takechee.qrcodereader.ui.common.navigation.DefaultNavigateHelper
import com.takechee.qrcodereader.ui.common.navigation.NavigateHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApp): Context {
        return application.applicationContext
    }

    @Provides
    fun providesConnectivityManager(context: Context): ConnectivityManager = requireNotNull(
        context.applicationContext.getSystemService()
    )

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }

    @Provides
    fun providesNavigateHelper(): NavigateHelper = DefaultNavigateHelper()

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.buildDatabase(context)

    @Singleton
    @Provides
    fun providesReadCodeDatabase(database: AppDatabase): ReadCodeDatabase {
        return ReadCodeRoomDatabase(database, database.readCodeFtsDao())
    }
}
