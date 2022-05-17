package com.mehmetth.itunessearch.data.common

import com.mehmetth.itunessearch.*
import com.mehmetth.itunessearch.data.search.api.SearchApi
import com.mehmetth.itunessearch.data.search.repository.SearchRemoteRepositoryImpl
import com.mehmetth.itunessearch.domain.search.repository.SearchRepositoryImpl
import com.mehmetth.itunessearch.data.search.repository.SearchRemoteRepository
import com.mehmetth.itunessearch.domain.search.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideSearchService(moshiConverterFactory: MoshiConverterFactory): SearchApi {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }).build())
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
            .create(SearchApi::class.java)
    }

    @Provides
    fun provideSearchRemoteRepository(searchApi : SearchApi): SearchRemoteRepository {
        return SearchRemoteRepositoryImpl(searchApi)
    }

    @Provides
    fun provideSearchRepository(searchRemoteRepository : SearchRemoteRepository): SearchRepository {
        return SearchRepositoryImpl(searchRemoteRepository)
    }
}


