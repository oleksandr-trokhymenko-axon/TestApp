package com.axon.testapp.di

import com.axon.testapp.data.remote.UserService
import com.axon.testapp.data.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

//    @Singleton
//    @Provides
//    fun provideUserRemoteDataSource(userService: UserService) = UserRemoteDataSource(userService)

    @Singleton
    @Provides
//    fun provideRepository(remoteDataSource: UserRemoteDataSource) = UserRepository(remoteDataSource)
    fun provideRepository(userService: UserService) = UserRepository(userService)
}