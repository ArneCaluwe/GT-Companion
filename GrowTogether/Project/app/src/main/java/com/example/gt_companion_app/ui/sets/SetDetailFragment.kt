package com.example.gt_companion_app.ui.sets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gt_companion_app.R
import com.example.gt_companion_app.databinding.SetDetailSmallFragmentBinding
import com.example.gt_companion_app.viewmodels.SetDetailViewModel
import org.koin.android.ext.android.inject

class SetDetailFragment : Fragment() {

    //val args : setDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: SetDetailViewModel by inject()
        val binding =  SetDetailSmallFragmentBinding.inflate(inflater, container, false)
        var scoreAthlete1 = binding.setDetailScoreAthlete1
        var scoreAthlete2 = binding.setDetailScoreAthlete2
        return binding.root
    }
}