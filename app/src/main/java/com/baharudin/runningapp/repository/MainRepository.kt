package com.baharudin.runningapp.repository

import com.baharudin.runningapp.db.Run
import com.baharudin.runningapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
        val runDAO: RunDAO
) {
    suspend fun insertRun(run : Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortedByDate()

    fun getAllRunsSortedByTimeMillis() = runDAO.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()

    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()

    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()

    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalCaloriesBUrned() = runDAO.getTotalCaloriesBurned()

    fun getTotalTimeMillis() = runDAO.getTotalTimeInMillis()




}