/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: CoroutineModule.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.48
 */

package id.co.ikonsultan.approval.core.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.co.ikonsultan.approval.core.common.coroutine.DefaultDispatcherProvider
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        impl: DefaultDispatcherProvider
    ): DispatcherProvider

}