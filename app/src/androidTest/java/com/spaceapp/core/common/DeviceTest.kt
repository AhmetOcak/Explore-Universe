package com.spaceapp.core.common

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.spaceapp.core.common.helper.Device
import com.spaceapp.core.common.helper.MobileServiceType
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceTest {

    @Test
    fun mobileServiceType_gmsHmsAvailable_returnGms() {
        val result = Device.mobileServiceType(ApplicationProvider.getApplicationContext())

        assertThat(result).isEqualTo(MobileServiceType.GMS)
    }

    @Test
    fun mobileServiceType_gmsAvailable_returnGms() {
        val result = Device.mobileServiceType(ApplicationProvider.getApplicationContext())

        assertThat(result).isEqualTo(MobileServiceType.GMS)
    }

    @Test
    fun mobileServiceType_hmsAvailable_returnHms() {
        val result = Device.mobileServiceType(ApplicationProvider.getApplicationContext())

        assertThat(result).isEqualTo(MobileServiceType.HMS)
    }

    @Test
    fun mobileServiceType_gmsHmsNotAvailable_returnNone() {
        val result = Device.mobileServiceType(ApplicationProvider.getApplicationContext())

        assertThat(result).isEqualTo(MobileServiceType.NONE)
    }
}