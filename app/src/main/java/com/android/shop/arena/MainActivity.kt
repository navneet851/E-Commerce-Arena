package com.android.shop.arena

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.theme.ArenaTheme
import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.android.shop.arena.notification.notificationChannel
import com.android.shop.arena.notification.showNotification

class MainActivity : ComponentActivity() {

    lateinit var dataStoreManager: DataStoreManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {
            showPermissionExplanationDialog()
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()
        notificationChannel(this)
        //showNotification(this)
        dataStoreManager = DataStoreManager(this)
        enableEdgeToEdge()
        setContent {

            ArenaTheme {
                App()
            }
        }
    }




    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun showPermissionExplanationDialog() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
            // Show rationale dialog and request permission again
            AlertDialog.Builder(this)
                .setTitle("Permission Required")
                .setMessage("This permission is needed to post notifications. Please allow.")
                .setPositiveButton("OK") { _, _ ->
                    requestNotificationPermission()
                }
                .create()
                .show()
        } else {
            // Guide user to app settings
            AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("You have denied the notification permission. Please enable it in app settings.")
                .setPositiveButton("Open Settings") { _, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
                .create()
                .show()
        }
    }



}

