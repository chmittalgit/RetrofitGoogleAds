package com.example.retrofitgoogleads.googleadsretrofit.view

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitgoogleads.AdsApplication
import com.example.retrofitgoogleads.R
import com.example.retrofitgoogleads.databinding.FragmentAdsListBinding
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel
import com.example.retrofitgoogleads.googleadsretrofit.utils.NetworkChangeReceiver
import com.example.retrofitgoogleads.googleadsretrofit.viewmodel.GoogleAdsViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AdsListFragment : Fragment(),
    NetworkChangeReceiver.ConnectivityReceiverListener {
    private lateinit var binding: FragmentAdsListBinding
    private lateinit var snackBar: Snackbar

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var adapter: AdsListAdapter

    private val viewModel by lazy {
        viewModelFactory.create(GoogleAdsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as AdsApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NetworkChangeReceiver.connectivityReceiverListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerNetworkingBroadcast()
        observeData()
    }

    //BroadCast receiver
    private fun registerNetworkingBroadcast() {
        requireContext().registerReceiver(
            NetworkChangeReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onDetach() {
        super.onDetach()
        requireContext().unregisterReceiver(NetworkChangeReceiver())
    }
    private fun observeData() {
        // View model observing data
        viewModel.adssLiveData.observe(viewLifecycleOwner) {
            var data: List<AdsUiModel>? = null
            data = if (it == null) {
                Log.d("TAG", "observeData: local car list empty")
                listOf()
            } else {
                Log.d("TAG", "observeData: local cars available ${it.size}")
                it
            }
            setupRecyclerView(data)
            viewModel.setProgressVisibility(false)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            progressVisibility(it)
        }

        viewModel.isAdssUpdatedLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.api_updated),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.error),
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewModel.getAllAdssLocally()
            viewModel.setProgressVisibility(false)
        }
    }
    // recyclerview process
    private fun setupRecyclerView(data: List<AdsUiModel>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            //recyclerview only
            adapter = AdsListAdapter(data)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            viewModel.fetchAdsRemotely()
            viewModel.setProgressVisibility(true)
        } else {
            viewModel.setProgressVisibility(false)
            showNetworkMessage(isConnected)
        }
    }

    private fun progressVisibility(isProgress: Boolean) {
        if (isProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    // show snackbar msg
    private fun showNetworkMessage(isConnected: Boolean) {
        //here root is parent layout of this fragment
        if (!isConnected) {
            snackBar = Snackbar.make(
                binding.root,
                resources.getString(R.string.offline),
                Snackbar.LENGTH_LONG
            )
            snackBar.setBackgroundTint(resources.getColor(R.color.black))
            snackBar.show()
        }
    }

}