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
import com.brian_big.gadsleaderboard.adapters.TopLearnersAdapter
import com.brian_big.gadsleaderboard.model.TopLearner
import com.brian_big.gadsleaderboard.services.LeadersClientInstance
import com.brian_big.gadsleaderboard.services.TopLearnerService
import kotlinx.android.synthetic.main.fragment_top_learners.*
import retrofit2.Call
import retrofit2.Response

class TopLearnersFragment : Fragment(){
    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTopLearners()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_top_learners, container, false)
        fetchTopLearners()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpRecyclerView(context: Context, topLearners: List<TopLearner>) {
        rvTopLearners.layoutManager = LinearLayoutManager(context)
        val adapter = TopLearnersAdapter(context,topLearners)
        rvTopLearners.adapter = adapter
    }

    private fun fetchTopLearners() {
        val service: TopLearnerService = LeadersClientInstance.retrofitInstance!!.create(
            TopLearnerService::class.java)
        val call = service.topLearners
        call.enqueue(object : retrofit2.Callback<List<TopLearner>>{
            override fun onFailure(call: Call<List<TopLearner>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<TopLearner>>,
                response: Response<List<TopLearner>>
            ) {
                if (response.isSuccessful){
                    val topLearners = response.body()
                    setUpRecyclerView(rootView.context, topLearners!!)
                }
            }
        })
    }
}