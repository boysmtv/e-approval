/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: User.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.19
 */

package id.co.ikonsultan.approval.core.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: String
)