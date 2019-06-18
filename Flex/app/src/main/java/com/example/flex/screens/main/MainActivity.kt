package com.example.flex.screens.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.flex.common.FragmentService
import com.example.flex.common.Utils
import com.example.flex.common.Utils.PREF_USER_FIRST_TIME
import com.example.flex.db.FlexClient
import com.example.flex.screens.main.downloads.DownloadsFragment
import com.example.flex.screens.main.library.LibraryFragment
import com.example.flex.screens.main.settings.*
import com.example.flex.screens.onboarding.OnboardingActivity
import com.example.flex.screens.player.VideoActivity
import com.ncapdevi.fragnav.FragNavController
import com.tonyodev.fetch2.Fetch
import com.tonyodev.fetch2.Request
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_movie_layout.view.*


class MainActivity : AppCompatActivity(),
    LibraryFragment.OnFragmentInteractionListener,
    DownloadsFragment.OnFragmentInteractionListener,
    SettingsFragment.OnFragmentInteractionListener,
    SettingsConnectionFragment.OnFragmentInteractionListener,
    SettingsAboutFragment.OnFragmentInteractionListener,
    FragNavController.TransactionListener
{

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
        val isUserFirstTime = Utils
            .readSharedSetting(this@MainActivity, PREF_USER_FIRST_TIME, "true")!!
            .toBoolean()

        val introIntent = Intent(this@MainActivity, OnboardingActivity::class.java)
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime)

        if (isUserFirstTime)
            startActivity(introIntent)
        // --------------------------------------

        setContentView(com.example.flex.R.layout.activity_main)

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

    fun onRefreshClicked(menuItem: MenuItem) {
        val fragment = fragments[0] as LibraryFragment
        fragment.viewModel.refresh()
    }

    fun onDownloadButtonClicked(view: View) {
        val url = FlexClient.getStreamingUrl(view.movieId.text.toString().toInt())
        val filename = view.movieName.toString()

        val request = Request(url.toString(), "/downloads/$filename")

        Fetch.getDefaultInstance().enqueue(request)
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
                isIconifiedByDefault = false
            }
        }
    }
    //endregion
}