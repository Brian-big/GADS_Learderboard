package com.brian_big.gadsleaderboard.model

import com.google.gson.annotations.SerializedName

data class TopLearner(@SerializedName("name") val name: String,
                      @SerializedName("hours") val learningHours: Int,
                      @SerializedName("country") val country: String,
                      @SerializedName("badgeUrl") val badgeUrl: String)

data class IQLeader(@SerializedName("name")val name: String,
                    @SerializedName("score") val score: Int,
                    @SerializedName("country") val country: String,
                    @SerializedName("badgeUrl") val badgeUrl: String)

