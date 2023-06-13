package com.example.retrofitgoogleads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.retrofitgoogleads.R
import com.example.retrofitgoogleads.databinding.ActivityAdListBinding

class AdListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAdListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val myNavController=navHostFragment.navController

        myNavController.addOnDestinationChangedListener{controller,dest,args->
            when(dest.id){

                R.id.adsListFragment->{

                }
                /*
                R.id.movieFragment->{

                }
                 */

            }
        }
    }
}