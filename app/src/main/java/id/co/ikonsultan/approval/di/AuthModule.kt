/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: AuthModule.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.25
 */

package id.co.ikonsultan.approval.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.co.ikonsultan.approval.core.data.datasource.AuthDataSource
import id.co.ikonsultan.approval.core.data.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(
        firestore: FirebaseFirestore
    ): AuthDataSource {
        return AuthDataSource(firestore)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        dataSource: AuthDataSource
    ): AuthRepository {
        return AuthRepository(dataSource)
    }
}