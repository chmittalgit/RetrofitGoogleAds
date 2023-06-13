package com.example.retrofitgoogleads.googleadsretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgoogleads.R
import com.example.retrofitgoogleads.databinding.AdsListViewBinding
import com.example.retrofitgoogleads.googleadsretrofit.model.AdsUiModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


class AdsListAdapter(private val ads:List<AdsUiModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // Constants to represent the view types
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_AD = 1

    // ViewHolder for the ad view
    class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private  var adView: AdView =itemView.findViewById(R.id.addView)
        // Bind the ad data here
        fun bind() {
            val adRequest = AdRequest.Builder().build()

            adView.loadAd(adRequest)
            // Bind the data to the view components
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      /*  val v=AdsListViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdsViewHolder(v)
       */
        return if (viewType == VIEW_TYPE_AD) {
            val adView =
                LayoutInflater.from(parent.context).inflate(R.layout.ad_layout, parent, false)
            AdViewHolder(adView)
        } else {

            val v = AdsListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AdsViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //  holder.bindItem(ads[position])
        if (position == ads.size - 1) {
            // listener.loadMoreDataToRecyclerView(position+1)
        }
        //added ad_google
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            val itemViewHolder = holder as AdsViewHolder
            val data = ads?.get(position - (position / 3))

            if (data != null) {
                itemViewHolder.bindItem(data)
            }
        } else {
            val adViewHolder = holder as AdViewHolder
            adViewHolder.bind()
        }

    }

    override fun getItemCount(): Int {
        return ads.size
    }
// add
    override fun getItemViewType(position: Int): Int {
        // Return a different view type for the ad item view
        return if ((position + 1) % 3 == 0) {
            VIEW_TYPE_AD
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun appendList(lst: List<AdsUiModel>?) {
        if (lst.isNullOrEmpty()) {
            return
        } else {
           // ads.addAll(lst)
        }
    }
/*
    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
      //  holder.bindItem(ads[position])
        if (position == ads.size - 1) {
           // listener.loadMoreDataToRecyclerView(position+1)
        }
        //added ad_google
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            val itemViewHolder = holder as AdsViewHolder
            val data = ads?.get(position - (position / 3))

            if (data != null) {
                itemViewHolder.bindItem(data)
            }
        } else {
            val adViewHolder = holder as AdViewHolder
            adViewHolder.bind()
        }
    }
 */
    inner class AdsViewHolder(private val itemBinding: AdsListViewBinding)
        :RecyclerView.ViewHolder(itemBinding.root) {
            fun bindItem(model:AdsUiModel){
                itemBinding.name.text=model.name
                itemBinding.startDate.text=model.startDate
                itemBinding.endDate.text=model.endDate
                //itemBinding.matchType.text=model.matches.

            }

    }

}