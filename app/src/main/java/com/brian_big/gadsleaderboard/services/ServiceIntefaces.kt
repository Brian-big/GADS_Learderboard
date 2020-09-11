package com.brian_big.gadsleaderboard.services

import com.brian_big.gadsleaderboard.model.IQLeader
import com.brian_big.gadsleaderboard.model.TopLearner
import retrofit2.Call
import retrofit2.http.GET


interface TopLearnerService{
    @get:GET("/api/hours")
    val topLearners: Call<List<TopLearner>>
}
interface IQLeaderService{
    @get:GET("/api/skilliq")
    val iqLeaders: Call<List<IQLeader>>
}