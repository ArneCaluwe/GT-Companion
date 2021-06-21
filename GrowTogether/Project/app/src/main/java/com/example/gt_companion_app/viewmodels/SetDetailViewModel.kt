package com.example.gt_companion_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.repos.SetRepository

class SetDetailViewModel(private val setRepository: SetRepository) : ViewModel() {
    private lateinit var _setDetail: LiveData<SetWithAthleteScores>
    val setDetail: LiveData<SetWithAthleteScores>
        get() = _setDetail

    fun updateSetDetail(setId: String) {
        _setDetail = setRepository.getSet(setId)
    }
}