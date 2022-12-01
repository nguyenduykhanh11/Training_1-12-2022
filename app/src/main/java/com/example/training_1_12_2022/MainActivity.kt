package com.example.training_1_12_2022

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.training_1_12_2022.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()
        setUpNavigationView()
        setUpShowWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpShowWebView() {
        with(binding){
            btnWebView.setOnClickListener {
                webview.apply {
                    webViewClient = WebViewClient()
                    loadUrl("https://www.youtube.com/")
                    settings.apply {
                        javaScriptEnabled = true
                        setSupportZoom(true)
                    }
                }
            }
        }
    }
    override fun onBackPressed() {
        with(binding.webview){
            if (canGoBack()) goBack() else onBackPressed()
        }
    }

    private fun setUpNavigationView() {
        binding.navView.setNavigationItemSelectedListener(this)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.layoutDrawer,
            binding.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.layoutDrawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_delete -> {
                showSnackBar("Click Snackbar delete", "Delete")
                true
            }
            R.id.menu_favorite -> {
                showSnackBar("Click Snackbar favorite", "Favorite")
                true
            }
            R.id.menu_share -> {
                showSnackBar("Click Snackbar favorite", "Share")
                true
            }
            else -> {false}
        }
    }

    private fun showSnackBar(message: String, buttom: String) {
        Snackbar.make(binding.layoutDrawer,message,Snackbar.LENGTH_SHORT).apply {
            setAction(buttom){}
            show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.sub_item1 -> {
                showToast("Click item item1")
                true
            }
            R.id.sub_item2 -> {
                showToast("Click item item2")
                true
            }
            R.id.sub_item3 -> {
                showToast("Click item item3")
                true
            }
            R.id.sub_item4 -> {
                showToast("Click item item4")
                true
            }
            else -> {false}
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

    }
}