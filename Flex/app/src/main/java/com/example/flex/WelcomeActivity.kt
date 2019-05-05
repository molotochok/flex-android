package com.example.flex

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.flex.fragments.*
import com.example.flex.services.FragmentService
import com.ncapdevi.fragnav.FragNavController
import org.jetbrains.anko.toast
import android.content.Intent
import android.widget.EditText
import com.example.flex.services.ShareService
import com.google.android.material.textfield.TextInputEditText


class WelcomeActivity : AppCompatActivity(), WelcomeFragment1.OnFragmentInteractionListener,
    WelcomeFragment2.OnFragmentInteractionListener, FragNavController.TransactionListener {
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

    private val fragments : List<Fragment> = listOf(
        WelcomeFragment1(),
        WelcomeFragment2()
    )

    private val fragmentService = FragmentService(supportFragmentManager, fragments)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        fragmentService.init(this, savedInstanceState)
    }


    //region Public Methods
    // WelcomeFragment1 event handlers
    fun onGetStartedBtnClicked(view : View){
        fragmentService.pushFragment(WelcomeFragment2())
    }

    // WelcomeFragment2 event handlers
    fun onBackBtnClicked(view : View){
        fragmentService.onBackPressed()
    }
    fun onNextBtnClicked(view : View){
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }

    fun onShare(view : View){
        val editText = view as TextInputEditText
        val data = editText.text.toString()

        val shareService = ShareService(this)
        shareService.share(data)
    }
    //endregion
}
