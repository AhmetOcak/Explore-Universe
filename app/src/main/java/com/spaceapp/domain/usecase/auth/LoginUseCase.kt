package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task as gmsTask
import com.huawei.hmf.tasks.Task as hmsTask
import com.google.firebase.auth.AuthResult
import com.huawei.agconnect.auth.SignInResult
import com.spaceapp.core.common.TaskResult
import com.spaceapp.domain.model.auth.Login
import com.spaceapp.domain.repository.AuthRepository
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {

    fun firebaseAuth(login: Login) : Flow<TaskResult<gmsTask<AuthResult>>> = flow {
        try {
            emit(TaskResult.Success(data = repository.loginGMS(login = login)))
        } catch (e: IOException) {
            emit(TaskResult.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(TaskResult.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(login: Login) : Flow<TaskResult<hmsTask<SignInResult>>> = flow {
        try {
            emit(TaskResult.Success(data = repository.loginHMS(login = login)))
        } catch (e: IOException) {
            emit(TaskResult.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(TaskResult.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}