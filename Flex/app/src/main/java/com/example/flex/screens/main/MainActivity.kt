package com.example.flex.screens.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import kotlinx.android.synthetic.main.adapter_movie_layout.view.*
import com.ncapdevi.fragnav.FragNavController
import androidx.fragment.app.Fragment
import com.example.flex.common.FragmentService
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flex.screens.player.VideoActivity
import com.example.flex.db.FlexClient
import com.example.flex.db.entity.Media
import com.example.flex.screens.main.settings.Settings
import com.example.flex.screens.main.settings.SettingsType
import com.example.flex.screens.main.downloads.DownloadsFragment
import com.example.flex.screens.main.library.LibraryFragment
import com.example.flex.screens.main.library.MediaViewModel
import com.example.flex.screens.main.settings.SettingsAboutFragment
import com.example.flex.screens.main.settings.SettingsConnectionFragment
import com.example.flex.screens.main.settings.SettingsFragment
import org.jetbrains.anko.toast
import com.example.flex.common.Utils
import com.example.flex.common.Utils.PREF_USER_FIRST_TIME
import com.example.flex.screens.onboarding.OnboardingActivity


class MainActivity : AppCompatActivity(),
    LibraryFragment.OnFragmentInteractionListener,
    DownloadsFragment.OnFragmentInteractionListener,
    SettingsFragment.OnFragmentInteractionListener,
    SettingsConnectionFragment.OnFragmentInteractionListener,
    SettingsAboutFragment.OnFragmentInteractionListener,
    FragNavController.TransactionListener
{
    private lateinit var viewModel : MediaViewModel

    private val settingsList = arrayListOf(
        Settings(
            1,
            "Server connection",
            com.example.flex.R.drawable.server_network_icon,
            SettingsType.ServerConnection
        ),
        Settings(
            2,
            "About",
            com.example.flex.R.drawable.about_icon,
            SettingsType.About
        )
    )

    //region Dependencies
    private val fragments = listOf(
        LibraryFragment(),
        DownloadsFragment(),
        SettingsFragment(settingsList)
    )

    private val fragmentService = FragmentService(supportFragmentManager, fragments)
    //endregion

    //region FragNavController.TransactionListener Implementation
    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }
    //endregion

    //region OnFragmentInteractionListener Implementation
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
    //region AppCompatActivity overrides
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Run OnBoarding activity if first time
        val isUserFirstTime =
            java.lang.Boolean.valueOf(Utils.readSharedSetting(this@MainActivity, PREF_USER_FIRST_TIME, "true"))

        val introIntent = Intent(this@MainActivity, OnboardingActivity::class.java)
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime)

        if (isUserFirstTime)
            startActivity(introIntent)
        // --------------------------------------

        setContentView(com.example.flex.R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MediaViewModel::class.java)
        viewModel.getAllMedia().observe(this, Observer {
            @Override
            fun onChanged(@Nullable media : List<Media>){
                toast("CHanged")
            }
        })

        // Set actionBar color
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(parseColor("#FFFFFFFF")))

        // FragmentService
        fragmentService.init(this, savedInstanceState)
        fragmentService.setOnNavigationItemSelectedListener(navigation)
    }

    override fun onBackPressed() {
        if(!fragmentService.onBackPressed())
            super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        fragmentService.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.flex.R.menu.options_menu, menu)

        createSearchOption(menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fragmentService.onOptionsItemSelected(item)
        return true
    }
    //endregion


    // <-------------------- Public methods -------------------->
    // Library Fragment events
    fun openVideoPlayer(view: View){
        val intent = Intent(this, VideoActivity::class.java)

        val streamingUrl = FlexClient.getStreamingUrl(view.movieId.text.toString().toInt())
        intent.putExtra("streamingUrl", streamingUrl.toString())

        startActivity(intent)
    }

    fun onCardViewClicked(view: View){
        val newFragment = LibraryFragment(1)

        fragmentService.pushFragment(newFragment)
    }

    // Settings Fragment events
    fun onSettingItemClicked(view: View){
        val settingsId = view.findViewById<TextView>(com.example.flex.R.id.settings_id).text.toString().toInt()

        val settings = settingsList.first{s -> s.id == settingsId}

        val newFragment = when(settings.settingsType) {
            SettingsType.ServerConnection -> SettingsConnectionFragment()
            SettingsType.About -> SettingsAboutFragment()
        }

        fragmentService.pushFragment(newFragment)
    }

    //region Private Methods
    private fun createSearchOption(menu : Menu?){
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            (menu.findItem(com.example.flex.R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setIconifiedByDefault(false)
            }
        }
    }
    //endregion
}