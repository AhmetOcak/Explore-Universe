package com.spaceapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.AGConnectUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel(){

    private val _currentUser = AGConnectAuth.getInstance().currentUser
    val currentUser: AGConnectUser = _currentUser
}