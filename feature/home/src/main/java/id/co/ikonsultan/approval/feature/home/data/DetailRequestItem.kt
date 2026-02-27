/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: DetailRequestItem.kt
 *
 * Last modified by Dedy Wijaya on 26/02/26 15.00
 */

package id.co.ikonsultan.approval.feature.home.data

data class DetailRequestItem(
    val ticketId: String = "",
    val staffName: String = "",
    val reportType: String = "",
    val notes: String = "",
    val fileName: String = "",
    val status: String = "",
    val slaList: List<DetailRequestSlaItem> = emptyList()
)