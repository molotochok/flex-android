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
import android.util.Log
import android.widget.TextView
import com.example.flex.R
import com.example.flex.screens.main.MainActivity
import com.example.flex.screens.scanner.ScanActivity
import com.example.flex.common.PermissionService
import com.example.flex.common.ShareService
import com.example.flex.common.Utils
import com.example.flex.common.Utils.PREF_DISPLAY_NAME_KEY
import com.example.flex.common.Utils.PREF_HOSTNAME_KEY
import com.example.flex.common.Utils.PREF_PORT_KEY
import com.google.android.material.textfield.TextInputEditText
import kotlin.reflect.KClass
import com.example.flex.common.Utils.PREF_USER_FIRST_TIME
import kotlinx.android.synthetic.main.fragment_welcome3.*
import org.jetbrains.anko.toast


class OnboardingActivity : AppCompatActivity(), OnboardingGetStartedFragment.OnFragmentInteractionListener,
    OnboardingDownloadFragment.OnFragmentInteractionListener, OnboardingConnectFragment.OnFragmentInteractionListener, FragNavController.TransactionListener {

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

    private lateinit var connectFragment: OnboardingConnectFragment
    private val fragments : List<Fragment> = listOf(
        OnboardingGetStartedFragment(),
        OnboardingDownloadFragment(),
        OnboardingConnectFragment()
    )

    private val fragmentService = FragmentService(supportFragmentManager, fragments)
    private val permissionService = PermissionService(this, Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.flex.R.layout.activity_welcome)

        fragmentService.init(this, savedInstanceState)
    }


    //region Public Methods
    // OnboardingGetStartedFragment event handlers
    fun onGetStartedBtnClicked(view : View){
        fragmentService.pushFragment(OnboardingDownloadFragment())
    }

    // OnboardingDownloadFragment event handlers
    fun onBackBtnClicked(view : View){
        fragmentService.onBackPressed()
    }
    fun onNextBtnClicked(view : View){
        connectFragment = OnboardingConnectFragment()
        fragmentService.pushFragment(connectFragment)
    }

    fun onShare(view : View){
        val editText = view as TextInputEditText
        val data = editText.text.toString()

        val shareService = ShareService(this)
        shareService.share(data)
    }

    // OnboardingConnectFragment event handlers
    fun onOpenQrCodeScanner(view:View){
        if (permissionService.checkAndRequestPermissions()){
            openActivity(ScanActivity::class)
        }
    }

    fun onConnectBtnClicked(view: View){
        val fragmentView = connectFragment.view!!

        val hostname = fragmentView.findViewById<TextInputEditText>(R.id.hostname_id).text.toString()
        val port = fragmentView.findViewById<TextInputEditText>(R.id.port_id).text.toString()
        val displayName = fragmentView.findViewById<TextInputEditText>(R.id.display_name_id).text.toString()

        if (hostname.isEmpty() or port.isEmpty() or displayName.isEmpty())
            return

        Utils.saveSharedSetting(this@OnboardingActivity, PREF_USER_FIRST_TIME, "false")
        Utils.saveSharedSetting(this@OnboardingActivity, PREF_HOSTNAME_KEY, hostname)
        Utils.saveSharedSetting(this@OnboardingActivity, PREF_PORT_KEY, port)
        Utils.saveSharedSetting(this@OnboardingActivity, PREF_DISPLAY_NAME_KEY, displayName)

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
