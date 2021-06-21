package com.example.gt_companion_app.data.local.sets

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.scores.AthleteScoreWithAthletes
import java.time.Duration

data class SetWithAthleteScores (
    @Embedded
    val set : Set,
    @Relation(entity = AthleteScore::class, parentColumn = "id", entityColumn = "set_id" )
    val athleteScores : List<AthleteScoreWithAthletes>
) {
    fun parseName() : String{
        return String.format("%s %s", "set", set.order.toString())
    }
    fun parseScoreAthlete(number: Int) : String {
        return athleteScores[number].athleteScore.score.toString()
    }
    fun parseRemainingTime() : String{
        val durationInSeconds = Duration.ofMillis(set.timeRemaining!!.toLong())
        return String.format("%s : %02d", durationInSeconds.toMinutes(), durationInSeconds.toMillis()/1000%60)
    }

    fun parseAthleteInitials(number: Int): String{
        var initials = ""
        val splits = athleteScores[number].athlete.fullName.split(" ")
        splits.forEach{string -> initials += string.elementAt(0)}
        return initials
    }
}