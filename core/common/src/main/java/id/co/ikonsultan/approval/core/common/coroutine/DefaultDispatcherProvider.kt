/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: DefaultDispatcherProvider.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.48
 */

package id.co.ikonsultan.approval.core.common.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultDispatcherProvider @Inject constructor() : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}