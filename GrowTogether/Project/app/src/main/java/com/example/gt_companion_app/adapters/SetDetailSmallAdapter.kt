package com.example.gt_companion_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.databinding.SetDetailSmallFragmentBinding
import com.example.gt_companion_app.model.sets.SetModel

class SmallSetDetailAdapter : ListAdapter<SetWithAthleteScores, SmallSetDetailViewHolder>(
    SmallSetDetailDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallSetDetailViewHolder {
        return SmallSetDetailViewHolder(
            SetDetailSmallFragmentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SmallSetDetailViewHolder, position: Int) {
        val setWithAthleteScores = getItem(position)
        print(setWithAthleteScores)
        (holder as SmallSetDetailViewHolder).bind(setWithAthleteScores)
    }
}

class SmallSetDetailViewHolder(
    private val binding: SetDetailSmallFragmentBinding
) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: SetWithAthleteScores) {
        binding.apply{
            set = item
        }
    }
}

private class SmallSetDetailDiffCallback : DiffUtil.ItemCallback<SetWithAthleteScores>(){
    override fun areItemsTheSame(oldItem: SetWithAthleteScores, newItem: SetWithAthleteScores): Boolean {
        if (oldItem.set.setId != newItem.set.setId)
            return false
        return true
    }

    override fun areContentsTheSame(oldItem: SetWithAthleteScores, newItem: SetWithAthleteScores): Boolean {
        return oldItem == newItem
    }
}