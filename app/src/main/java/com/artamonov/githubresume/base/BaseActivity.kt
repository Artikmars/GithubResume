package com.artamonov.githubresume.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var snackbar: Snackbar? = null

    override fun onResume() {
        super.onResume()

        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(networkCallback)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.unregisterNetworkCallback(networkCallback)
            }
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            snackbar?.dismiss()
        }

        override fun onLost(network: Network) {
            snackbar = Snackbar.make(
                findViewById(android.R.id.content), "Internet connection is missing",
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar?.show()
        }
    }
}
