package com.spaceapp.presentation.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.signup.SignUpViewModel

private const val hmsVerificationMessage = "Please type the verification code sent to"
private const val gmsVerificationMessage = "A verification link has been sent to your email account. The email may go into spam."

@Composable
fun VerifyEmailSection(
    modifier: Modifier,
    viewModel: SignUpViewModel,
    navController: NavController
) {
    if (viewModel.device == MobileServiceType.HMS) {
        VerifyEmailCard(
            modifier = modifier.fillMaxSize(),
            userEmail = viewModel.userEmail,
            value = viewModel.verifyCode,
            onValueChanged = { viewModel.updateVerifyCodeField(newValue = it) },
            onClick = {
                viewModel.signUp()
            }
        )
    } else {
        VerifyEmailCard(
            modifier = modifier.fillMaxSize(),
            userEmail = viewModel.userEmail,
            onClick = {
                navController.navigate(NavScreen.LoginScreen.route) {
                    popUpTo(0)
                }
            },
            isTextFieldAvailable = false
        )
    }
}

@Composable
private fun VerifyEmailCard(
    modifier: Modifier,
    userEmail: String,
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    onClick: () -> Unit,
    isTextFieldAvailable: Boolean = true
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .padding(horizontal = 32.dp),
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(15)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier.padding(vertical = 16.dp).size(120.dp),
                    painter = painterResource(id = R.drawable.verify),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = if (isTextFieldAvailable) "$hmsVerificationMessage $userEmail" else gmsVerificationMessage,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center
                )
                if (isTextFieldAvailable) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        value = value,
                        onValueChange = onValueChanged,
                        label = {
                            Text(text = "verify code", style = MaterialTheme.typography.body2)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        maxLines = 1,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_check_box),
                                contentDescription = null
                            )
                        },
                        textStyle = MaterialTheme.typography.body1,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = MaterialTheme.colors.secondary,
                            focusedBorderColor = MaterialTheme.colors.secondary,
                            focusedLabelColor = MaterialTheme.colors.secondary,
                        )
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    onClick = onClick,
                    content = {
                        Text(text = if(isTextFieldAvailable) "Verify" else "OK")
                    },
                    shape = RoundedCornerShape(50),
                    enabled = true,
                )
            }
        }
    }
}