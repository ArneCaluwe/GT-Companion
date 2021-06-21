package com.example.gt_companion_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gt_companion_app.data.local.bouts.BoutWithSets
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.databinding.BoutDetailSmallFragmentBinding
import com.example.gt_companion_app.databinding.SetDetailSmallFragmentBinding
import com.example.gt_companion_app.ui.boutDetail.BoutDetailSmallFragment

class BoutDetailSmallAdapter(private var boutClickListener: BoutClickListener) : ListAdapter<BoutWithSets, SmallBoutDetailViewHolder>(
    SmallBoutDetailDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallBoutDetailViewHolder {
        return SmallBoutDetailViewHolder(
            BoutDetailSmallFragmentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SmallBoutDetailViewHolder, position: Int) {
        val boutWithSets = getItem(position)
        (holder as SmallBoutDetailViewHolder).bind(boutWithSets, boutClickListener)
    }
}

class SmallBoutDetailViewHolder(
    private val binding: BoutDetailSmallFragmentBinding
) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: BoutWithSets, listener : BoutClickListener) {
        binding.apply{
            bout = item
        }

        itemView.setOnClickListener{
            listener.onBoutClicked(item)
        }
    }

}

private class SmallBoutDetailDiffCallback : DiffUtil.ItemCallback<BoutWithSets>(){
    override fun areItemsTheSame(oldItem: BoutWithSets, newItem: BoutWithSets): Boolean {
        if (oldItem.bout.boutId != newItem.bout.boutId)
            return false
        return true
    }

    override fun areContentsTheSame(oldItem: BoutWithSets, newItem: BoutWithSets): Boolean {
        return oldItem == newItem
    }
}

interface BoutClickListener {
    fun onBoutClicked(bout: BoutWithSets)
}