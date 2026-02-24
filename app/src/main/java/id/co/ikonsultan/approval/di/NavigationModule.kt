/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: NavigationModule.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.45
 */

package id.co.ikonsultan.approval.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.navigation.AppNavigatorImpl

@Module
@InstallIn(FragmentComponent::class)
object NavigationModule {

    @Provides
    fun provideNavigator(fragment: Fragment): AppNavigator {
        return AppNavigatorImpl(fragment.findNavController())
    }

}