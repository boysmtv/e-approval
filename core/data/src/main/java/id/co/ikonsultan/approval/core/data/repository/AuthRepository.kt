/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: AuthRepository.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.26
 */

package id.co.ikonsultan.approval.core.data.repository

import id.co.ikonsultan.approval.core.data.datasource.AuthDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val dataSource: AuthDataSource
)