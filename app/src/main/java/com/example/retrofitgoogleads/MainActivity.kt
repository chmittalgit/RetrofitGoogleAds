package com.example.retrofitgoogleads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.retrofitgoogleads.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

//id is different
const val AD_UNIT_ID = "ca-app-pub-7106981806500353/7793354177"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var interstitialAd: InterstitialAd? = null
    private var TAG = "MainActivity"
    private var adIsLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textName.setOnClickListener {
            val intent = Intent(this, AdListActivity::class.java)
            startActivity(intent)
        }


        val adRequest = AdRequest.Builder().build()
        val adRequest1 = AdRequest.Builder().build()

        binding.addView.loadAd(adRequest)
        binding.addView1.loadAd(adRequest1)

        loadInterId()
    }

    private fun loadInterId() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, AD_UNIT_ID, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.message.let {
                    Log.d(TAG, it)
                }
                interstitialAd = null
                adIsLoading = false
                Toast.makeText(this@MainActivity, "adLoaded()", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun continueTOApp(view: View) {
        showAd()
    }

    private fun showAd() {
        if (interstitialAd !== null) {
            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    Log.d(TAG, "AdClicked")
                }

                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "ad dismiss fullscreen content")
                    interstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.d(TAG, "failed to show in full screen")
                    interstitialAd = null
                }
            }
            interstitialAd!!.show(this)
        } else {
            Toast.makeText(this, "Failed error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        binding.addView.destroy()
        binding.addView1.destroy()
        super.onDestroy()
    }
}