package com.example.gt_companion_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gt_companion_app.data.local.bouts.BoutWithSets
import com.example.gt_companion_app.data.local.scores.AthleteScoreWithAthletes
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.repos.AthleteScoreRepository
import com.example.gt_companion_app.repos.BoutRepository
import com.example.gt_companion_app.repos.SetRepository
import kotlinx.coroutines.launch

class BoutDetailViewModel (
    private var setRepository: SetRepository,
    private var boutRepository: BoutRepository,
    private var athleteScoreRepository: AthleteScoreRepository
) : ViewModel() {
    /// BOUTS
    private lateinit var _bout: LiveData<BoutWithSets>
    val bout: LiveData<BoutWithSets>
        get() = _bout

    fun updateBout(boutId: String) {
        _bout = boutRepository.getBout(boutId)
        updateActiveSet(boutId)
    }
    /// BUTTONS
    val manualModelToggled : MutableLiveData<Boolean> = MutableLiveData(false)

    /// CURRENT SET
    private lateinit var _activeSet: LiveData<SetWithAthleteScores>
    val activeSet: LiveData<SetWithAthleteScores>
        get() = _activeSet

    fun updateActiveSet(boutId : String) {
        viewModelScope.launch {
            _activeSet = setRepository.getActiveSetForBout(boutId)
            updateActiveScores(boutId)
        }
    }

    private lateinit var _activeScores: LiveData<List<AthleteScoreWithAthletes>>
    val activeScores: LiveData<List<AthleteScoreWithAthletes>>
        get() = _activeScores

    fun updateActiveScores(id : String) {
        _activeScores = athleteScoreRepository.getScoresForActiveSetInBout(id)
    }
    fun updateActiveSetTime(time: Long){
        _activeSet.value?.set?.timeRemaining = time.toDouble()
    }

    fun saveSetChanges() {
        viewModelScope.launch {
            setRepository.updateSet(_activeSet.value!!)
        }
    }

    fun saveScoreChanges() {
        viewModelScope.launch {
            athleteScoreRepository.updateScores(_activeScores.value!!.map { aS -> aS.athleteScore })
        }
    }

    fun saveBoutChanges() {
        viewModelScope.launch {
            boutRepository.update(_bout.value!!.bout)
        }
    }
}