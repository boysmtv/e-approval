/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: DispatcherProvider.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.48
 */

package id.co.ikonsultan.approval.core.common.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}