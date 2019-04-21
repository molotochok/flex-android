package com.example.flex.services

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

// Is capable of asking for granting permissions
class PermissionService (activity: Activity, vararg permissionTypes: String) {

    private val activity = activity
    private val permissionTypes = permissionTypes

    // Returns true if granted and false if not
    fun checkPermission(permissionType: String):Boolean{
        val permission = ContextCompat.checkSelfPermission(activity, permissionType)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    // Public methods
    fun checkAndRequestPermissions(): Boolean {
        val listPermissionsNeeded = ArrayList<String>()

        // Check for permissions
        for (p in permissionTypes) {
            if(!checkPermission(p)){
                listPermissionsNeeded.add(p)
            }
        }

        // Grand permissions
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    companion object {
        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    }
}