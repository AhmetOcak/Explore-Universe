package com.spaceapp.core.common.helper

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import com.google.android.gms.common.ConnectionResult as GoogleConnectionResult
import com.huawei.hms.api.ConnectionResult as HuaweiConnectionResult

enum class MobileServiceType {
    HMS,
    GMS,
    NONE
}

object Device {
    fun mobileServiceType(
        context: Context,
        firstPriority: MobileServiceType? = null
    ): MobileServiceType {

        val gmsAvailable: Boolean = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context) == GoogleConnectionResult.SUCCESS

        val hmsAvailable: Boolean = HuaweiApiAvailability.getInstance()
            .isHuaweiMobileServicesAvailable(context) == HuaweiConnectionResult.SUCCESS

        return if (gmsAvailable && hmsAvailable) getMobileServiceTypeByPriority(firstPriority)
        else if (gmsAvailable) return MobileServiceType.GMS
        else if (hmsAvailable) return MobileServiceType.HMS
        else MobileServiceType.NONE
    }

    private fun getMobileServiceTypeByPriority(firstPriority: MobileServiceType?): MobileServiceType {
        firstPriority?.let { return it } ?: return MobileServiceType.HMS
    }
}