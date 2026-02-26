/*
 * Project: Boys.mtv@gmail.com
 * File: RequestItem.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.35
 */

package id.co.ikonsultan.approval.feature.home.data

data class RequestItem(
    val id: String,
    val title: String,
    val status: String,
    val date: String,
    val requester: String,
    val approver: String,
    val department: String,
)