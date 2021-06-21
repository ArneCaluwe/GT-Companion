package com.example.gt_companion_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.growtogether.utils.Resource
import com.example.gt_companion_app.data.local.bouts.BoutWithSets
import com.example.gt_companion_app.repos.BoutRepository

class BoutListViewModel(boutRepository: BoutRepository) : ViewModel() {
    var bouts: LiveData<Resource<List<BoutWithSets>>> = boutRepository.getBouts();
}