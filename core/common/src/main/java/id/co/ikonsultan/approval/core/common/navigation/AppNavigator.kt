/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: AppNavigator.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.41
 */

package id.co.ikonsultan.approval.core.common.navigation

interface AppNavigator {
    fun openLogin()
    fun openHome()
    fun openSearch()
    fun openMail()
    fun openSetting()
    fun openHistory()
    fun openChecker()
    fun openCheckerDetail(id: String)
    fun backToHome()
    fun back()
}