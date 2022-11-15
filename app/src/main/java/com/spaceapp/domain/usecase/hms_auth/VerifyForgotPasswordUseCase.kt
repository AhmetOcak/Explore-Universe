package com.spaceapp.domain.usecase.hms_auth

import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.HmsAuthRepositoryImpl
import com.spaceapp.domain.model.hms.VerifyForgotPassword
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class VerifyForgotPasswordUseCase @Inject constructor(private val authRepository: HmsAuthRepositoryImpl) {

    operator fun invoke(verifyForgotPassword: VerifyForgotPassword): Flow<Result<Task<VerifyCodeResult>>> =
        flow {
            try {
                emit(Result.Success(data = authRepository.verifyEmail(verifyForgotPassword = verifyForgotPassword)))
            } catch (e: IOException) {
                emit(Result.Error(message = ERROR.INTERNET))
            } catch (e: Exception) {
                emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
            }
        }
}