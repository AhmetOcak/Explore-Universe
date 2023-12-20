package com.spaceapp.presentation.forgot_password.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.core.designsystem.component.DefaultButton
import com.spaceapp.core.designsystem.theme.White
import com.spaceapp.presentation.forgot_password.ForgotPasswordViewModel
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants

// For GMS
@Composable
fun SendPasswordResetMail(
    modifier: Modifier,
    viewModel: ForgotPasswordViewModel,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            painter = painterResource(id = R.drawable.forgot_password_email),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = ForgotPasswordScreenConstants.forgot_password_email,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = White
        )
        DefaultButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {
                navController.navigate(NavScreen.LoginScreen.route) {
                    popUpTo(0)
                }
            },
            contentText = if (viewModel.device == MobileServiceType.HMS)
                ForgotPasswordScreenConstants.send_email_hms else ForgotPasswordScreenConstants.sent_email_gms
        )
    }
}