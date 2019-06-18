package com.example.flex.screens.onboarding

import android.Manifest
import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.flex.common.FragmentService
import com.ncapdevi.fragnav.FragNavController
import android.content.Intent
import com.example.flex.R
import com.example.flex.screens.main.MainActivity
import com.example.flex.screens.scanner.ScanActivity
import com.example.flex.common.PermissionService
import com.example.flex.common.ShareService
import com.google.android.material.textfield.TextInputEditText
import kotlin.reflect.KClass


class OnboardingActivity : AppCompatActivity(), OnboardingWelcomeFragment.OnFragmentInteractionListener,
    OnboardingWebsiteFragment.OnFragmentInteractionListener, OnboardingSettingsFragment.OnFragmentInteractionListener, FragNavController.TransactionListener {

    //region Overrides

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        if(supportActionBar != null)
            fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }

    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        if(supportActionBar != null)
            fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }

    // For Qr Code scanner
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for(p in permissions){
            when(p){
                Manifest.permission.CAMERA ->{
                    if(permissionService.checkPermission(p))
                        openActivity(ScanActivity::class)
                }
            }
        }
    }

    //endregion

    private val fragments : List<Fragment> = listOf(
        OnboardingWelcomeFragment(),
        OnboardingWebsiteFragment(),
        OnboardingSettingsFragment()
    )

    private val fragmentService = FragmentService(supportFragmentManager, fragments)
    private val permissionService = PermissionService(this, Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        fragmentService.init(this, savedInstanceState)
    }


    //region Public Methods
    // OnboardingWelcomeFragment event handlers
    fun onGetStartedBtnClicked(view : View){
        fragmentService.pushFragment(OnboardingWebsiteFragment())
    }

    // OnboardingWebsiteFragment event handlers
    fun onBackBtnClicked(view : View){
        fragmentService.onBackPressed()
    }
    fun onNextBtnClicked(view : View){
        fragmentService.pushFragment(OnboardingSettingsFragment())
    }

    fun onShare(view : View){
        val editText = view as TextInputEditText
        val data = editText.text.toString()

        val shareService = ShareService(this)
        shareService.share(data)
    }

    // OnboardingSettingsFragment event handlers
    fun onOpenQrCodeScanner(view:View){
        if (permissionService.checkAndRequestPermissions()){
            openActivity(ScanActivity::class)
        }
    }

    fun onConnectBtnClicked(view: View){
        //TODO: Perform connection here

        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }
    //endregion

    //region Private methods
    private fun<T> openActivity(activityClass: KClass<T>) where T: Activity {
        val intent = Intent(this, activityClass.java)
        startActivity(intent)
    }
    //endregion
}
