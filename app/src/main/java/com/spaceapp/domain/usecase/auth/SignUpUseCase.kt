package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.huawei.agconnect.auth.SignInResult
import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.FirebaseAuthRepositoryImpl
import com.spaceapp.data.repository.HmsAuthRepositoryImpl
import com.spaceapp.domain.model.auth.SignUp
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl,
    private val hmsAuthRepositoryImpl: HmsAuthRepositoryImpl
) {
    fun firebaseAuth(signUp: SignUp) : Flow<Result<Task<AuthResult>>> = flow {
        try {
            emit(Result.Success(data = firebaseAuthRepositoryImpl.signUp(signUp = signUp)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(signUp: SignUp) : Flow<Result<com.huawei.hmf.tasks.Task<SignInResult>>> = flow {
        try {
            emit(Result.Success(data = hmsAuthRepositoryImpl.signUp(signUp = signUp)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}