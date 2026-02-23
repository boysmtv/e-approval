/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: UserMapper.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.20
 */

package id.co.ikonsultan.approval.core.domain.mapper

import id.co.ikonsultan.approval.core.domain.model.User
import id.co.ikonsultan.approval.core.data.dto.UserDto

fun UserDto.toDomain(id: String): User {
    return User(
        id = id,
        name = name.orEmpty(),
        email = email.orEmpty(),
        role = role.orEmpty()
    )
}