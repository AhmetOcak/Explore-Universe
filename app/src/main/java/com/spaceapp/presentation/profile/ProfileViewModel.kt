package com.spaceapp.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        setUserType()
    }

    var userName by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateUserNameValue(newValue: String) {
        userName = newValue
    }

    fun updatePasswordValue(newValue: String) {
        password = newValue
    }

    private fun setUserType() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            _uiState.update {
                it.copy(
                    currentUserType = UserType.GMS,
                    profileName = FirebaseAuth.getInstance().currentUser?.email
                )
            }
        } else if (AGConnectAuth.getInstance().currentUser != null) {
            _uiState.update {
                it.copy(
                    currentUserType = UserType.HMS,
                    profileName = AGConnectAuth.getInstance().currentUser.email
                )
            }
        }
    }

    fun logOut() {
        when (_uiState.value.currentUserType) {
            UserType.GMS -> {
                FirebaseAuth.getInstance().signOut()
            }
            UserType.HMS -> {
                AGConnectAuth.getInstance().signOut()
            }
            else -> {}
        }
    }
}

data class UiState(
    val currentUserType: UserType? = null,
    val profileName: String? = null
)

sealed interface UserType {
    data object GMS : UserType
    data object HMS : UserType
}