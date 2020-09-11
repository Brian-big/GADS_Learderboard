package com.brian_big.gadsleaderboard.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brian_big.gadsleaderboard.R
import com.brian_big.gadsleaderboard.adapters.TopIQAdapter
import com.brian_big.gadsleaderboard.model.IQLeader
import com.brian_big.gadsleaderboard.services.IQLeaderService
import com.brian_big.gadsleaderboard.services.LeadersClientInstance
import kotlinx.android.synthetic.main.fragment_top_iq.*
import retrofit2.Call
import retrofit2.Response

class TopIQFragment : Fragment(){
    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_top_iq, container, false)
        fetchIQLeaders()
        return rootView
    }

    private fun setUpRecyclerView(context: Context , iqLeaders: List<IQLeader>) {
        rvTopIQ.layoutManager = LinearLayoutManager(context)
        val adapter = TopIQAdapter(context, iqLeaders)
        rvTopIQ.adapter = adapter
    }
    private fun fetchIQLeaders() {
        val service = LeadersClientInstance.retrofitInstance!!.create(IQLeaderService::class.java)
        val call = service.iqLeaders
        call.enqueue(object : retrofit2.Callback<List<IQLeader>>{
            override fun onFailure(call: Call<List<IQLeader>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<IQLeader>>,
                response: Response<List<IQLeader>>
            ) {
                if (response.isSuccessful){
                    val iqLeaders = response.body()
                    setUpRecyclerView(rootView.context, iqLeaders!!)
                }
               else{
                }
            }

        })
    }
}