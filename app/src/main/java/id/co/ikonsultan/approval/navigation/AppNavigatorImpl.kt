/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: AppNavigatorImpl.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.41
 */

package id.co.ikonsultan.approval.navigation

import androidx.navigation.NavController
import id.co.ikonsultan.approval.R
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val navController: NavController
) : AppNavigator {

    override fun openHome() {
        navController.navigate(R.id.homeFragment)
    }

}