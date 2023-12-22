package com.spaceapp.presentation.profile

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.spaceapp.R
import com.spaceapp.core.designsystem.components.DefaultButton
import com.spaceapp.core.designsystem.components.DefaultTextField
import com.spaceapp.core.designsystem.components.Gif
import com.spaceapp.core.designsystem.theme.BlueHaze
import com.spaceapp.core.designsystem.theme.Mirage
import com.spaceapp.core.designsystem.theme.White500

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogOutClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var settingType by remember { mutableStateOf(SettingType.CHANGE_USER_NAME) }

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    if (showDialog) {
        when (settingType) {
            SettingType.CHANGE_USER_NAME -> {
                AccountDialog(
                    onDismissRequest = {
                        showDialog = false
                        viewModel.updateUserNameValue("")
                    },
                    onValueChange = { viewModel.updateUserNameValue(it) },
                    placeHolderText = "User name",
                    onApplyClick = {},
                    onCancelClick = {
                        viewModel.updateUserNameValue("")
                        showDialog = false
                    },
                    title = "Change user name",
                    value = viewModel.userName
                )
            }

            SettingType.CHANGE_PROFILE_IMG -> {
                ProfileImageDialog(
                    onDismissRequest = { showDialog = false },
                    onGalleryClick = { /*TODO: open gallery */ },
                    onCameraClick = { /*TODO: open camera */ }
                )
            }

            SettingType.DELETE_ACCOUNT -> {
                AccountDialog(
                    onDismissRequest = {
                        showDialog = false
                        viewModel.updatePasswordValue("")
                    },
                    onValueChange = { viewModel.updatePasswordValue(it) },
                    placeHolderText = "Password",
                    onApplyClick = { /*TODO: delete account*/ },
                    onCancelClick = {
                        viewModel.updatePasswordValue("")
                        showDialog = false
                    },
                    title = "Delete Account",
                    value = viewModel.password
                )
            }
        }
    }

    ProfileScreenContent(
        modifier = modifier,
        onLogOutClick = {
            viewModel.logOut()
            onLogOutClick()
        },
        profileName = uiState.profileName ?: "Space Traveler",
        onSettingClick = {
            settingType = it
            showDialog = true
        }
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    onLogOutClick: () -> Unit,
    profileName: String,
    onSettingClick: (SettingType) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            IconButton(onClick = onLogOutClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_logout),
                    contentDescription = null,
                    tint = BlueHaze
                )
            }
        }
        ProfilePreviewSection(profileName = profileName, onSettingClick = onSettingClick)
    }
}

@Composable
private fun ProfilePreviewSection(profileName: String, onSettingClick: (SettingType) -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserProfileImage(modifier = Modifier.clip(CircleShape))
            UserProfileName(
                modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
                profileName = profileName
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(settingsList, key = { it.title }) {
                SettingsItem(
                    name = it.title,
                    icon = it.icon,
                    onClick = {
                        onSettingClick(it.settingType)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsItem(name: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = White500)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Text(text = name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun UserProfileImage(modifier: Modifier) {
    Gif(modifier = modifier, gifId = R.drawable.profile)
}

@Composable
private fun UserProfileName(modifier: Modifier, profileName: String) {
    Text(
        modifier = modifier,
        text = profileName,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun AccountDialog(
    onDismissRequest: () -> Unit,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    onApplyClick: () -> Unit,
    onCancelClick: () -> Unit,
    title: String,
    value: String
) {
    Dialog(onDismissRequest = onDismissRequest) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Mirage)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Start
                )
                DefaultTextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeHolderText = placeHolderText
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DefaultButton(onClick = onCancelClick, contentText = "Cancel")
                    DefaultButton(onClick = onApplyClick, contentText = "Apply")
                }
            }
        }
    }
}

@Composable
private fun ProfileImageDialog(
    onDismissRequest: () -> Unit,
    onGalleryClick: () -> Unit,
    onCameraClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Mirage)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Change Profile Image",
                        style = MaterialTheme.typography.displayMedium
                    )
                    IconButton(onClick = onDismissRequest) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null,
                            tint = BlueHaze
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "From where do you want to take the photo",
                    style = MaterialTheme.typography.displayMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DefaultButton(onClick = onGalleryClick, contentText = "Gallery")
                    Spacer(modifier = Modifier.width(16.dp))
                    DefaultButton(onClick = onCameraClick, contentText = "Camera")
                }
            }
        }
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}

private val settingsList = listOf(
    Settings(
        title = "Change user name",
        icon = Icons.Filled.AccountCircle,
        settingType = SettingType.CHANGE_USER_NAME,
        labelText = "User name"
    ),
    Settings(
        title = "Change profile image",
        icon = Icons.Filled.AccountCircle,
        settingType = SettingType.CHANGE_PROFILE_IMG,
        labelText = ""
    ),
    Settings(
        title = "Delete account",
        icon = Icons.Filled.Delete,
        settingType = SettingType.DELETE_ACCOUNT,
        labelText = "Password"
    )
)

private data class Settings(
    val title: String,
    val icon: ImageVector,
    val settingType: SettingType,
    val labelText: String
)

private enum class SettingType {
    CHANGE_USER_NAME,
    CHANGE_PROFILE_IMG,
    DELETE_ACCOUNT
}