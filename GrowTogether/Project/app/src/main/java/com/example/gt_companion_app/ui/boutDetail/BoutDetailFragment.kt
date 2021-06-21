package com.example.gt_companion_app.ui.boutDetail

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.example.gt_companion_app.R
import com.example.gt_companion_app.adapters.SmallSetDetailAdapter
import com.example.gt_companion_app.data.local.bouts.BoutWithSets
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.databinding.BoutDetailFragmentBinding
import com.example.gt_companion_app.viewmodels.BoutDetailViewModel
import kotlinx.coroutines.coroutineScope
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import java.lang.IllegalArgumentException
import java.time.Duration
import java.util.stream.Collectors
import kotlin.coroutines.coroutineContext

class BoutDetailFragment : Fragment() {

    private lateinit var adapter: SmallSetDetailAdapter

    private lateinit var countdownTimer : Chronometer
    private var timeTicking : Boolean = false;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args: BoutDetailFragmentArgs by navArgs()
        val viewModel: BoutDetailViewModel by inject ()
        val binding = BoutDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.updateBout(args.boutId)
        viewModel.bout.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    updateBout(it, binding, viewModel)
                }
            }
        )
        viewModel.activeSet.observe(viewLifecycleOwner, {
            updateSet( binding, viewModel)
        })
        viewModel.activeScores.observe(viewLifecycleOwner, {
            updateScores(binding, viewModel)
        })

        linkManualScoringButtons(binding, viewModel)

        binding.btnStart.setOnClickListener{
            timeTicking = !timeTicking
            if(timeTicking) {
                countdownTimer.start()
                binding.btnStart.text = getString(R.string.button_stop)

                val currentTime = viewModel.activeSet?.value?.set?.timeRemaining!!.toLong()
                print(currentTime)
                countdownTimer.base = SystemClock.elapsedRealtime() + viewModel.activeSet?.value?.set?.timeRemaining!!.toLong()
            }else {
                countdownTimer.stop()
                binding.btnStart.text = getString(R.string.button_start)
            }
        }



        /**
         * Add buttons for
         * switch to manual
         * cancel bout
         * reorder sets
         *
         */
        binding.expandableFab.setOnClickListener{
            var toast : Toast = Toast(context)
            toast.setText("Add later")
            toast.show()
        }

        adapter = SmallSetDetailAdapter()
        binding.boutDetailSetsList.adapter = adapter

        val toggleManual = binding.fabSwitchManual
        toggleManual.setOnClickListener {
            viewModel.manualModelToggled.value = !viewModel.manualModelToggled.value!!
        }

        binding.fabNext.setOnClickListener{
            viewModel.bout.value!!.bout.activeSet+=1
            viewModel.saveBoutChanges()
        }



        return binding.root
    }

    private fun updateBout(data: BoutWithSets, binding: BoutDetailFragmentBinding, viewModel: BoutDetailViewModel) {
        binding.currentSet.setDetailMatch.text = data.bout.name
        adapter.submitList(data.sets.sortedBy { s -> s.set.order })

    }

    private fun updateSet( binding: BoutDetailFragmentBinding, viewModel: BoutDetailViewModel) {
        val data = viewModel.activeSet.value!!
        binding.currentSet.setDetailSet.text = data.parseName()

        countdownTimer = binding.currentSet.countdownTimer
        countdownTimer.base = SystemClock.elapsedRealtime() + data.set.timeRemaining!!.toLong()

        countdownTimer.setOnChronometerTickListener {
            if(it.base - SystemClock.elapsedRealtime() <= 0){
                it.stop()
                binding.btnStart.isEnabled = false
                binding.btnStart.text = getString(R.string.button_start)
            }
        }
    }

    private fun updateScores(binding: BoutDetailFragmentBinding, viewModel: BoutDetailViewModel){
        binding.currentSet.setDetailScoreAthlete1.text = viewModel.activeScores.value!![0].athleteScore.score.toString()
        binding.currentSet.setDetailScoreAthlete2.text = viewModel.activeScores.value!![1].athleteScore.score.toString()
    }

    private fun showProgress(b: Boolean) {

    }

    private fun linkManualScoringButtons(
        binding: BoutDetailFragmentBinding,
        viewModel: BoutDetailViewModel
    ) {
        val btnScoreLeft = binding.boutDetailAddLeft
        val btnScoreRight = binding.boutDetailAddRight
        val btnScoreBoth = binding.boutDetailAddBoth

        val manualButtonBar = binding.manualButtonBar;
        viewModel.manualModelToggled.observe(viewLifecycleOwner, {

            if (it)
                manualButtonBar.visibility = LinearLayout.VISIBLE
            else
                manualButtonBar.visibility = LinearLayout.GONE

        })
        btnScoreLeft.setOnClickListener{
            addScoreToAthlete(0, viewModel, binding)
        }
        btnScoreRight.setOnClickListener{
            addScoreToAthlete(1,viewModel, binding)
        }
        btnScoreBoth.setOnClickListener{
            addScoreToAthlete(-1, viewModel, binding)
        }
    }

    private fun addScoreToAthlete(athleteNumber: Int, viewModel: BoutDetailViewModel, binding: BoutDetailFragmentBinding){
        if(athleteNumber == -1){
            viewModel.activeScores.value!![0].athleteScore.score += 1
            viewModel.activeScores.value!![1].athleteScore.score += 1
            viewModel.saveScoreChanges()
        } else if(arrayOf(0,1).contains(athleteNumber)) {
            viewModel.activeScores.value!![athleteNumber].athleteScore.score += 1
        } else {
            throw IllegalArgumentException(String.format("value %s not in domain", athleteNumber))
        }
        viewModel.saveScoreChanges()
        binding.currentSet.setChronoDisplay.base = SystemClock.elapsedRealtime() + 6000
        binding.currentSet.setChronoDisplay.visibility = Chronometer.VISIBLE
        binding.currentSet.setChronoDisplay.start()
        stopCountdown(viewModel)
        disableButtons(binding)

        binding.currentSet.setChronoDisplay.setOnChronometerTickListener {
            if (it.base <= SystemClock.elapsedRealtime() + 1000){
                it.stop()
                it.visibility = Chronometer.GONE
                enableButtons(binding)
                startCountdown(viewModel)
            }
        }
    }

    private fun disableButtons(binding: BoutDetailFragmentBinding) {
        binding.btnStart.isEnabled = false
        binding.boutDetailAddLeft.isEnabled = false
        binding.boutDetailAddBoth.isEnabled = false
        binding.boutDetailAddRight.isEnabled = false
    }
    private fun enableButtons(binding: BoutDetailFragmentBinding) {
        binding.btnStart.isEnabled = true
        binding.boutDetailAddLeft.isEnabled = true
        binding.boutDetailAddBoth.isEnabled = true
        binding.boutDetailAddRight.isEnabled = true
    }

    private fun stopCountdown(viewModel: BoutDetailViewModel){
        countdownTimer.stop()
        viewModel.updateActiveSetTime(countdownTimer.base - SystemClock.elapsedRealtime())
        viewModel.saveSetChanges()
    }
    private fun startCountdown(viewModel: BoutDetailViewModel){
        countdownTimer.start()
        countdownTimer.base = SystemClock.elapsedRealtime() + viewModel.activeSet.value?.set?.timeRemaining!!.toLong()
    }
}