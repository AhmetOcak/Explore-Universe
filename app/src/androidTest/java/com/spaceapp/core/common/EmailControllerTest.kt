package com.spaceapp.core.common

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailControllerTest {

    @Test
    fun emailController_appropriateEmailType_returnTrue() {
        val email = "ahmetocak754@gmail.com"

        val result = EmailController.emailController(email)

        assertThat(result).isTrue()
    }

    @Test
    fun emailController_inappropriateEmailType_returnFalse() {
        val email = "ahmet ocak"

        val result = EmailController.emailController(email)

        assertThat(result).isFalse()
    }
}