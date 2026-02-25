/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: AppNavigator.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.41
 */

package id.co.ikonsultan.approval.core.common.navigation

interface AppNavigator {
    fun openHome()
    fun openLogin()
    fun openHistory()
    fun backToHome()
    fun openRequest()
    fun openDetail(id: String)
    fun back()
}