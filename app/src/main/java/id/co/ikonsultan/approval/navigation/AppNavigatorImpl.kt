/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: AppNavigatorImpl.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.41
 */

package id.co.ikonsultan.approval.navigation

import android.os.Bundle
import androidx.navigation.NavController
import id.co.ikonsultan.approval.R
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val navController: NavController
) : AppNavigator {

    override fun openLogin() {
        navController.navigate(R.id.action_splash_to_auth)
    }

    override fun openHome() {
        navController.navigate(R.id.action_login_to_main)
    }

    override fun openSearch() {
        navController.navigate(R.id.searchFragment)
    }

    override fun openMail() {
        navController.navigate(R.id.mailFragment)
    }

    override fun openSetting() {
        navController.navigate(R.id.searchFragment)
    }

    override fun openHistory() {
        navController.navigate(R.id.historyFragment)
    }

    override fun openChecker() {
        navController.navigate(R.id.requestFragment)
    }

    override fun openCheckerDetail(id: String) {
        val bundle = Bundle().apply {
            putString("id", id)
        }
        navController.navigate(
            R.id.action_requestFragment_to_detailRequestFragment,
            bundle
        )
    }

    override fun backToHome() {
        navController.popBackStack(R.id.homeFragment, false)
    }

    override fun back() {
        navController.navigateUp()
    }
}