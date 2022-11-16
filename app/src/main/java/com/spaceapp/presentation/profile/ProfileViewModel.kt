package com.spaceapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.AGConnectUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel(){

    private val _currentUserHms = AGConnectAuth.getInstance().currentUser
    val currentUserHms: AGConnectUser? = _currentUserHms

    private val _currentUserGms = FirebaseAuth.getInstance().currentUser
    val currentUserGms: FirebaseUser? = _currentUserGms

}