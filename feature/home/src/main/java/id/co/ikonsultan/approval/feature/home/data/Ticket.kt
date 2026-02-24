/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: Ticket.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.43
 */

package id.co.ikonsultan.approval.feature.home.data

data class Ticket(
    val title: String,
    val date: String,
    val status: String,
    val role: String
)