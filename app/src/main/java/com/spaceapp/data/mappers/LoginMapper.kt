package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.remote.hms.entity.LoginDto
import com.spaceapp.domain.model.hms.Login

fun Login.toLoginDto(): LoginDto {
    return LoginDto(
        userEmail = userEmail,
        userPassword = password
    )
}