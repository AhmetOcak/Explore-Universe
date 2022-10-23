package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.remote.hms.entity.UserDto
import com.spaceapp.domain.model.User

fun User.toUserDto(): UserDto {
    return UserDto(
        userEmail = userEmail,
        userPassword = userPassword
    )
}