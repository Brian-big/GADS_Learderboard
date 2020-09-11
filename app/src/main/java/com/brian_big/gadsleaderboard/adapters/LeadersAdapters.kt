package com.brian_big.gadsleaderboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brian_big.gadsleaderboard.R
import com.brian_big.gadsleaderboard.model.IQLeader
import com.brian_big.gadsleaderboard.model.TopLearner

class TopLearnersAdapter(private val context: Context, private val topLearners: List<TopLearner>)
    : RecyclerView.Adapter<TopLearnersAdapter.ViewHolder>() {

    private lateinit var topLearner: TopLearner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.learning_leader_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return topLearners.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        topLearner = topLearners[position]
        holder.tvName.text = topLearner.name
        holder.tvHours.text =
            "${topLearner.learningHours} learning hours, ${topLearner.country}"

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvHours: TextView = itemView.findViewById(R.id.tvHours)
    }
}

class TopIQAdapter(private val context: Context, private val iqLeaders: List<IQLeader>) : RecyclerView.Adapter<TopIQAdapter.ViewHolder>() {

    private lateinit var iqLeader: IQLeader
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.skill_iq_leader_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return iqLeaders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        iqLeader = iqLeaders[position]
        holder.tvName.text = iqLeader.name
        holder.tvScore.text = "${iqLeader.score} IQ Skill score, ${iqLeader.country}"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvScore: TextView = itemView.findViewById(R.id.tvScore)
    }
}